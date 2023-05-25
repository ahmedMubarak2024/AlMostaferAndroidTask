package com.mub.almostaferandroidtask.features.home.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.mub.almostaferandroidtask.bases.BaseViewModel
import com.mub.almostaferandroidtask.features.home.repo.MovieRepo
import com.mub.almostaferandroidtask.helpers.MainPagingSource
import com.mub.almostaferandroidtask.model.Constants
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val movieRepo: MovieRepo by inject()

    fun loadPubMovies() {
        viewModelScope.launchWithErrorHandling {
            movieRepo.loadPopularMovies(1)
        }
    }

    fun observePubMovies() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(Constants.SORTED_BY_POPULARITY) {
            movieRepo.loadPopularMovies(it)
        }
    }.flow.cachedIn(viewModelScope)

    fun observeAverageVoteMovies() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(Constants.SORTED_BY_AVERAGE_VOTES) {
            movieRepo.loadVoteAverageMovies(it)
        }
    }.flow.cachedIn(viewModelScope)

    fun observeRevenueMovies() = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        MainPagingSource(Constants.SORTED_BY_REVENUE) {
            movieRepo.loadRevenueMovies(it)
        }
    }.flow.cachedIn(viewModelScope)
}