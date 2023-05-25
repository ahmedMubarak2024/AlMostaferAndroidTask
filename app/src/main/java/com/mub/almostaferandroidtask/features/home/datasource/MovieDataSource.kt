package com.mub.almostaferandroidtask.features.home.datasource

import com.mub.almostaferandroidtask.features.home.models.Movie

class MovieDataSource(
    private val onlineSource: MovieOnlineSource,
    private val movieLocalSource: MovieLocalSource
) {
    /*
    * load a page of movies and insert them in the database
    * if the movie in the database we append the sortedby string to the movie as it
    * is repeated but in different category
    * */
    suspend fun getMovieList(pageNum: Int, sortedBy: String) {
        val res = onlineSource.getMovies(pageNum, sortedBy)
        res.results?.map {
            Movie(
                it.title,
                it.overview,
                it.voteAverage,
                it.posterPath,
                sortedBy,
                it.id
            )
        }?.forEach {
            try {
                movieLocalSource.insert(it)
            } catch (e: java.lang.Exception) {
                val movie = movieLocalSource.getMovieById(it.id ?: 0)
                if (movie != null && movie.sortedBy != sortedBy) {
                    movie.sortedBy = movie.sortedBy + sortedBy
                    movieLocalSource.insertOrReplace(movie)
                }

            }
        }
    }
   

}