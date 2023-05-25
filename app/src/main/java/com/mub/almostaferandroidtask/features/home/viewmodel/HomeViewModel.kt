package com.mub.almostaferandroidtask.features.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.mub.almostaferandroidtask.bases.BaseViewModel
import com.mub.almostaferandroidtask.features.home.repo.MovieRepo
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val movieRepo: MovieRepo by inject()

    fun loadPubMovies() {
        viewModelScope.launchWithErrorHandling {
            val res = movieRepo.loadPopularMovies(1)
            println(res)
        }
    }
}