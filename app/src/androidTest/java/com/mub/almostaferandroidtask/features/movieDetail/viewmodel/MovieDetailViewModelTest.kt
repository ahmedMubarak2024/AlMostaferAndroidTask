package com.mub.almostaferandroidtask.features.movieDetail.viewmodel

import com.mub.almostaferandroidtask.helper.BaseRoomTest
import com.mub.almostaferandroidtask.helper.TestUtil
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MovieDetailViewModelTest : BaseRoomTest() {
    private val movieDetailViewModel = MovieDetailViewModel()


    @Test
    fun testLoadMovie_whenItIsInDataBase() {
        runBlocking {
            val item = TestUtil.getSingleMovie(1)
            movieDao.insert(item)
            Assert.assertNotNull(movieDetailViewModel.loadMovie(1))
            Assert.assertEquals(item, movieDetailViewModel.loadMovie(1))
        }
    }

    @Test
    fun testLoadMovie_whenItIsNotDataBase() {
        runBlocking {
            Assert.assertNull(movieDetailViewModel.loadMovie(1))
        }
    }
}