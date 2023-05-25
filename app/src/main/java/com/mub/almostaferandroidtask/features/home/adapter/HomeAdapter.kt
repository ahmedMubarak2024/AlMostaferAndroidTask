package com.mub.almostaferandroidtask.features.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mub.almostaferandroidtask.databinding.CellMovieBinding
import com.mub.almostaferandroidtask.features.home.models.Movie
import com.mub.almostaferandroidtask.features.home.view.HomeFragmentDirections


class HomeAdapter : PagingDataAdapter<Movie, HomeAdapter.MainViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
                oldItem == newItem
        }
    }

    inner class MainViewHolder(private val binding: CellMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(movie: Movie) {
            binding.movie = movie
            binding.imageView.setOnClickListener { binding.root.callOnClick() }
            binding.root.setOnClickListener {
                binding.root.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                        movie.id ?: 0
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            CellMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        getItem(position)?.let { holder.bindData(it) }
    }
}