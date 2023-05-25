package com.mub.almostaferandroidtask.features.home.viewmodel

import androidx.paging.AsyncPagingDataDiffer
import com.mub.almostaferandroidtask.helper.BaseRoomTest
import com.mub.almostaferandroidtask.helper.TestDiffCallback
import com.mub.almostaferandroidtask.helper.TestListCallback
import com.mub.almostaferandroidtask.model.Constants
import com.mub.almostaferandroidtask.model.comman.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.koin.core.component.inject
import kotlin.time.Duration.Companion.seconds

class HomeViewModelTest : BaseRoomTest() {
    private val homeViewModel: HomeViewModel by inject()
    private val differ = AsyncPagingDataDiffer(
        diffCallback = TestDiffCallback<Movie>(),
        updateCallback = TestListCallback(),
        workerDispatcher = Dispatchers.Main
    )

    suspend fun delayTime(): String {
        Thread.sleep(1000)
        return ""
    }

    @Test
    fun testObservePubMovies_whenPubMoviesInDataBase() {
        addPubMovies()
        val differ = AsyncPagingDataDiffer(
            diffCallback = TestDiffCallback<Movie>(),
            updateCallback = TestListCallback(),
            workerDispatcher = Dispatchers.Main
        )
        runTest(timeout = 60.seconds) {
            val job = launch {
                differ.submitData(homeViewModel.observePubMovies().firstOrNull()!!)
            }
            awaitAll(async { delayTime() })
            Assert.assertTrue(
                "should return list not empty",
                differ.snapshot().items.isNotEmpty()
            )
            job.cancel()
        }
    }

    @Test
    fun testObservePubMovies_onlyReturnPubMoviesInDataBase() {
        addPubMovies()
        addRevenueMovies()
        addAverageVoteMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observePubMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertFalse(
                    "shouldOnlyReturnPubMovies",
                    differ.snapshot().items.any { it.sortedBy != Constants.SORTED_BY_POPULARITY })
                job.cancel()
            }
        }
    }

    @Test
    fun testObservePubMovies_returnEmptyListWhenNoPubMovies() {
        addRevenueMovies()
        addAverageVoteMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observePubMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertTrue("shouldReturnEmptyList", differ.snapshot().items.isEmpty())
                job.cancel()
            }
        }
    }

    @Test
    fun testAddRevenueMovies_whenRevenueMoviesInDataBase() {
        addRevenueMovies()
        val differ = AsyncPagingDataDiffer(
            diffCallback = TestDiffCallback<Movie>(),
            updateCallback = TestListCallback(),
            workerDispatcher = Dispatchers.Main
        )
        runTest(timeout = 60.seconds) {
            val job = launch {
                differ.submitData(homeViewModel.observeRevenueMovies().firstOrNull()!!)
            }
            awaitAll(async { delayTime() })
            Assert.assertTrue(
                "should return list not empty",
                differ.snapshot().items.isNotEmpty()
            )
            job.cancel()
        }
    }

    @Test
    fun testAddRevenueMovies_onlyReturnRevenueMoviesInDataBase() {
        addPubMovies()
        addRevenueMovies()
        addAverageVoteMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observeRevenueMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertFalse(
                    "shouldOnlyReturnPubMovies",
                    differ.snapshot().items.any { it.sortedBy != Constants.SORTED_BY_POPULARITY })
                job.cancel()
            }
        }
    }

    @Test
    fun testAddRevenueMovies_returnEmptyListWhenNoRevenueMovies() {
        addPubMovies()
        addAverageVoteMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observeRevenueMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertTrue("shouldReturnEmptyList", differ.snapshot().items.isEmpty())
                job.cancel()
            }
        }
    }

    @Test
    fun testAddAverageVoteMovies_whenAverageVoteMoviesInDataBase() {
        addAverageVoteMovies()
        val differ = AsyncPagingDataDiffer(
            diffCallback = TestDiffCallback<Movie>(),
            updateCallback = TestListCallback(),
            workerDispatcher = Dispatchers.Main
        )
        runTest(timeout = 60.seconds) {
            val job = launch {
                differ.submitData(homeViewModel.observeAverageVoteMovies().firstOrNull()!!)
            }
            awaitAll(async { delayTime() })
            Assert.assertTrue(
                "should return list not empty",
                differ.snapshot().items.isNotEmpty()
            )
            job.cancel()
        }
    }

    @Test
    fun testAddAverageVoteMovies_onlyReturnAverageVoteMoviesInDataBase() {
        addPubMovies()
        addRevenueMovies()
        addAverageVoteMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observeAverageVoteMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertFalse(
                    "shouldOnlyReturnPubMovies",
                    differ.snapshot().items.any { it.sortedBy != Constants.SORTED_BY_POPULARITY })
                job.cancel()
            }
        }
    }

    @Test
    fun testAddAverageVoteMovies_returnEmptyListWhenNoAverageVoteMovies() {
        addPubMovies()
        addRevenueMovies()
        runTest(timeout = 60.seconds) {
            homeViewModel.observeAverageVoteMovies().firstOrNull()?.let {
                val job = launch {
                    differ.submitData(it)
                }
                delayTime()
                Assert.assertTrue("shouldReturnEmptyList", differ.snapshot().items.isEmpty())
                job.cancel()
            }
        }
    }


}