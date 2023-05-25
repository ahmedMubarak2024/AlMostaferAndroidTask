package com.mub.almostaferandroidtask.features.home.repo

import com.mub.almostaferandroidtask.bases.BaseRepo
import com.mub.almostaferandroidtask.features.home.datasource.MovieDataSource
import com.mub.almostaferandroidtask.model.Constants

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
}