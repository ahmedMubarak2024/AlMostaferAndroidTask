package com.mub.almostaferandroidtask.di

import com.mub.almostaferandroidtask.database.MovieDatabase
import com.mub.almostaferandroidtask.features.home.datasource.MovieDataSource
import com.mub.almostaferandroidtask.features.home.datasource.MovieOnlineSource
import com.mub.almostaferandroidtask.features.home.repo.MovieRepo
import com.mub.almostaferandroidtask.features.home.viewmodel.HomeViewModel
import com.mub.almostaferandroidtask.network.RetroFit
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

// any model related to network should be added here
val networkModule = module {
    single { RetroFit.mRetroFit }
    single { get<Retrofit>().create(MovieOnlineSource::class.java) }
}

val dataSourceModule = module {
    single { MovieDataSource(get(), get()) }
}
val repoModule = module {
    factory { MovieRepo(get()) }
}

val viewModels = module {
    viewModel { HomeViewModel() }
}
val databaseModel = module {
    single { MovieDatabase.getInstance(get()) }
    single { get<MovieDatabase>().movieDao() }
}

val models = networkModule + dataSourceModule + repoModule + viewModels + databaseModel