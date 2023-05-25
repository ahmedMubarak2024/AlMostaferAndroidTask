package com.mub.almostaferandroidtask.features.movieDetail.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.mub.almostaferandroidtask.R
import com.mub.almostaferandroidtask.bases.BaseFragment
import com.mub.almostaferandroidtask.databinding.FragmentMovieDetailBinding
import com.mub.almostaferandroidtask.features.movieDetail.viewmodel.MovieDetailViewModel
import kotlinx.coroutines.launch

class MovieDetailFragment : BaseFragment<MovieDetailViewModel, FragmentMovieDetailBinding>() {
    override val viewModel: MovieDetailViewModel by viewModels()
    private val fragArgs by navArgs<MovieDetailFragmentArgs>()

    override fun getLayoutId(): Int {
        return R.layout.fragment_movie_detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            getBinding().movie = viewModel.loadMovie(fragArgs.movieId)

        }
    }


}