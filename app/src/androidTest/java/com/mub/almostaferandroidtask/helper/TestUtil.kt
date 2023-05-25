package com.mub.almostaferandroidtask.helper

import com.mub.almostaferandroidtask.model.Constants
import com.mub.almostaferandroidtask.model.comman.Movie

object TestUtil {
    fun getListOfTestMovies(): List<Movie> =
        mutableListOf<Movie>().apply {
            add(Movie("Test1", "OverView1", 1.0, "poster1", Constants.SORTED_BY_POPULARITY, 1))
            add(Movie("Test2", "OverView2", 2.0, "poster2", Constants.SORTED_BY_POPULARITY, 2))
            add(Movie("Test3", "OverView3", 3.0, "poster3", Constants.SORTED_BY_POPULARITY, 3))
            add(Movie("Test4", "OverView4", 4.0, "poster4", Constants.SORTED_BY_POPULARITY, 4))
        }

    fun getMovieList(size: Int, sortedBy: String) =
        mutableListOf<Movie>().apply {
            for (i in 0 until size)
                add(Movie("Test$i", "OverView$i", 1.0 + i, "poster$i", sortedBy))
        }

    fun getSingleMovie(id: Int) = Movie("T", "o", 1.0, "p", Constants.SORTED_BY_REVENUE, id)
}