package com.mub.almostaferandroidtask.helper

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mub.almostaferandroidtask.database.MovieDatabase
import com.mub.almostaferandroidtask.features.home.datasource.MovieLocalSource
import com.mub.almostaferandroidtask.model.Constants
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import java.io.IOException

@RunWith(AndroidJUnit4::class)
abstract class BaseRoomTest : KoinComponent {

    protected lateinit var movieDao: MovieLocalSource
    private lateinit var db: MovieDatabase

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, MovieDatabase::class.java
        ).build()
        movieDao = db.movieDao()
        loadKoinModules(module(override = true) {
            single { db }
            single { movieDao }
        })
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    protected fun addPubMovies() {
        runBlocking {
            TestUtil.getMovieList(50, Constants.SORTED_BY_POPULARITY).forEach {
                movieDao.insert(it)
            }
        }
    }

    protected fun addRevenueMovies() {
        runBlocking {
            TestUtil.getMovieList(50, Constants.SORTED_BY_REVENUE).forEach {
                movieDao.insert(it)
            }
        }
    }

    protected fun addAverageVoteMovies() {
        runBlocking {
            TestUtil.getMovieList(50, Constants.SORTED_BY_AVERAGE_VOTES).forEach {
                movieDao.insert(it)
            }
        }
    }

}