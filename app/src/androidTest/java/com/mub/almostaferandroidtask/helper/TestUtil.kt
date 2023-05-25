package com.mub.almostaferandroidtask.helper

import com.mub.almostaferandroidtask.features.home.models.Movie
import com.mub.almostaferandroidtask.model.Constants

object TestUtil {
    fun getListOfTestMovies(): List<Movie> =
        mutableListOf<Movie>().apply {
            add(Movie("Test1", "OverView1", 1.0, "poster1", Constants.SORTED_BY_POPULARITY, 1))
            add(Movie("Test2", "OverView2", 2.0, "poster2", Constants.SORTED_BY_POPULARITY, 2))
            add(Movie("Test3", "OverView3", 3.0, "poster3", Constants.SORTED_BY_POPULARITY, 3))
            add(Movie("Test4", "OverView4", 4.0, "poster4", Constants.SORTED_BY_POPULARITY, 4))
        }
}