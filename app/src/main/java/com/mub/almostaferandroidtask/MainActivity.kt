package com.mub.almostaferandroidtask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mub.almostaferandroidtask.features.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewmodel by viewModel<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewmodel.loadPubMovies()
    }
}