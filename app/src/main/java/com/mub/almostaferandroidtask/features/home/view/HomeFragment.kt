package com.mub.almostaferandroidtask.features.home.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mub.almostaferandroidtask.R
import com.mub.almostaferandroidtask.bases.BaseFragment
import com.mub.almostaferandroidtask.databinding.FragmentHomeBinding
import com.mub.almostaferandroidtask.features.home.adapter.HomeAdapter
import com.mub.almostaferandroidtask.features.home.viewmodel.HomeViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    private val pubAdapter = HomeAdapter()
    private val topRatedAdapter = HomeAdapter()
    private val revueAdapter = HomeAdapter()
    override val viewModel: HomeViewModel by viewModels()
    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getBinding().rvMoviesPub.adapter = pubAdapter
        getBinding().rvMoviesTopRated.adapter = topRatedAdapter
        getBinding().rvMoviesRevenu.adapter = revueAdapter
        observerForError(pubAdapter)
        observerForError(topRatedAdapter)
        observerForError(revueAdapter)

        viewModel.observePubMovies().onEach { pubAdapter.submitData(it) }.launchIn(lifecycleScope)
        viewModel.observeRevenueMovies().onEach { revueAdapter.submitData(it) }
            .launchIn(lifecycleScope)
        viewModel.observeAverageVoteMovies().onEach { topRatedAdapter.submitData(it) }
            .launchIn(lifecycleScope)

    }

    private fun observerForError(adapter: HomeAdapter) {
        adapter.addLoadStateListener { loadState ->
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                AlertDialog.Builder(requireContext())
                    .setMessage(errorState.error.message)
                    .setPositiveButton("Retry") { _, _ ->
                        adapter.retry()
                    }
                    .setCancelable(false)
                    .create()
                    .show()

            }

        }
    }

}