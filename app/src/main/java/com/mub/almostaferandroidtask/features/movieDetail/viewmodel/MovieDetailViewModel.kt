package com.mub.almostaferandroidtask.features.movieDetail.viewmodel

import com.mub.almostaferandroidtask.bases.BaseViewModel
import com.mub.almostaferandroidtask.features.home.repo.MovieRepo
import com.mub.almostaferandroidtask.model.comman.Movie
import org.koin.core.component.inject

class MovieDetailViewModel : BaseViewModel() {
    private val movieRep by inject<MovieRepo>()
    suspend fun loadMovie(movieId: Int): Movie? {
        return movieRep.getMovieById(movieId)
    }
}