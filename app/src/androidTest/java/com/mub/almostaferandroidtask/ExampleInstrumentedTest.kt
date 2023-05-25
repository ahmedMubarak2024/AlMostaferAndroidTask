package com.mub.almostaferandroidtask

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mub.almostaferandroidtask.database.MovieDatabase
import com.mub.almostaferandroidtask.features.home.datasource.MovieLocalSource
import com.mub.almostaferandroidtask.helper.TestUtil
import com.mub.almostaferandroidtask.model.Constants
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class
ExampleInstrumentedTest {
    private lateinit var movieDao: MovieLocalSource
    private lateinit var db: MovieDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java
        ).build()
        movieDao = db.movieDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val movies = TestUtil.getListOfTestMovies()
        movies.forEach {
            runBlocking {
                movieDao.insert(it)

            }
        }
        runBlocking {
            Assert.assertTrue(
                "Should be not Empty",
                movieDao.getPagedList(10, 0, Constants.SORTED_BY_POPULARITY).isNotEmpty()
            )
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.mub.almostaferandroidtask", appContext.packageName)
    }
}