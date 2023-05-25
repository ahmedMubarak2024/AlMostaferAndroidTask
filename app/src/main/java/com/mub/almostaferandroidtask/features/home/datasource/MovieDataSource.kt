package com.mub.almostaferandroidtask.features.home.datasource

class MovieDataSource(private val onlineSource: MovieOnlineSource) {
    suspend fun getMovieList(pageNum: Int, sortedBy: String) =
        onlineSource.getMovies(pageNum, sortedBy)

}