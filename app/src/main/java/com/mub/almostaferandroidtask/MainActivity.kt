package com.mub.almostaferandroidtask

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.mub.almostaferandroidtask.databinding.ActivityMainBinding
import com.mub.almostaferandroidtask.features.home.adapter.HomeAdapter
import com.mub.almostaferandroidtask.features.home.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewmodel by viewModel<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val adapter = HomeAdapter()
        binding.rvMovies.adapter = adapter
        adapter.addLoadStateListener { loadState ->
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                AlertDialog.Builder(this)
                    .setMessage(errorState.error.message)
                    .setPositiveButton("Ok") { _, _ ->
                        adapter.retry()
                    }
                    .setCancelable(false)
                    .create()
                    .show()


            }

        }

        viewmodel.observePubMovies().asLiveData(Dispatchers.Main)
            .observe(this) {
                lifecycleScope.launch {
                    adapter.submitData(it)

                }
            }

    }
}