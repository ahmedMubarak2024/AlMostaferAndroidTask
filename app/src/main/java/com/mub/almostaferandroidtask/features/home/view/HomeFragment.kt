package com.mub.almostaferandroidtask.features.home.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mub.almostaferandroidtask.R
import com.mub.almostaferandroidtask.bases.BaseFragment
import com.mub.almostaferandroidtask.databinding.FragmentHomeBinding
import com.mub.almostaferandroidtask.features.home.adapter.HomeAdapter
import com.mub.almostaferandroidtask.features.home.viewmodel.HomeViewModel
import com.mub.almostaferandroidtask.helpers.ErrorSnackBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    private val pubAdapter = HomeAdapter()
    private val topRatedAdapter = HomeAdapter()
    private val revueAdapter = HomeAdapter()
    override val viewModel: HomeViewModel by viewModels()
    private lateinit var errorSnackBar: ErrorSnackBar
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        errorSnackBar = ErrorSnackBar(getBinding().root)
        getBinding().rvMoviesPub.adapter = pubAdapter
        getBinding().rvMoviesTopRated.adapter = topRatedAdapter
        getBinding().rvMoviesRevenu.adapter = revueAdapter
        observerForError(pubAdapter, getBinding().pbPub)
        observerForError(topRatedAdapter, getBinding().pbTopRated)
        observerForError(revueAdapter, getBinding().pbRevenue)

        viewModel.observePubMovies().onEach { pubAdapter.submitData(it) }.launchIn(lifecycleScope)
        viewModel.observeRevenueMovies().onEach { revueAdapter.submitData(it) }
            .launchIn(lifecycleScope)
        viewModel.observeAverageVoteMovies().onEach { topRatedAdapter.submitData(it) }
            .launchIn(lifecycleScope)

    }

    private fun observerForError(adapter: HomeAdapter, progressBar: ProgressBar) {
        adapter.addLoadStateListener { loadState ->
            progressBar.isVisible =
                loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading || loadState.prepend is LoadState.Loading

            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }

            errorState?.let {
                progressBar.postDelayed({
                    errorSnackBar.addError(adapter, it.error.message ?: "Error")
                }, 400)
            } ?: kotlin.run {
                errorSnackBar.removeError(adapter)
            }

        }
    }


}