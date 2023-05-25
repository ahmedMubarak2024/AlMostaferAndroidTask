package com.mub.almostaferandroidtask.features.home.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mub.almostaferandroidtask.bases.BaseRepo
import com.mub.almostaferandroidtask.features.home.datasource.MovieDataSource
import com.mub.almostaferandroidtask.helpers.MainPagingSource
import com.mub.almostaferandroidtask.model.Constants
import com.mub.almostaferandroidtask.model.comman.Movie
import kotlinx.coroutines.CoroutineScope

class MovieRepo(private val movieDataSource: MovieDataSource) : BaseRepo() {

    suspend fun loadPopularMovies(pageNum: Int) =
        backgroundContext { movieDataSource.getMovieList(pageNum, Constants.SORTED_BY_POPULARITY) }

    suspend fun loadRevenueMovies(pageNum: Int) =
        backgroundContext { movieDataSource.getMovieList(pageNum, Constants.SORTED_BY_REVENUE) }

    suspend fun loadVoteAverageMovies(pageNum: Int) = backgroundContext {
        movieDataSource.getMovieList(
            pageNum,
            Constants.SORTED_BY_AVERAGE_VOTES
        )
    }

    fun observePubMovies(scope: CoroutineScope) = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(Constants.SORTED_BY_AVERAGE_VOTES) {
            loadVoteAverageMovies(it)
        }
    }.flow.cachedIn(scope)

    suspend fun getMovieById(movieId: Int): Movie? {
        return movieDataSource.getMovieById(movieId)
    }
}