package com.mub.almostaferandroidtask.network

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//contains Retrofit creation
object RetroFit {
    private val baseUrl = "https://api.themoviedb.org/3/"

    val mRetroFit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        //this part for added the api_key in all the requests without needing to provide it in every function
        .client(OkHttpClient.Builder().addInterceptor {
            val request = it.request().newBuilder().url(
                it.request().url().newBuilder()
                    .addQueryParameter("api_key", "114fe6670282f6a632638661e5e86dee").build()
            ).build()
            Log.i("RetroFit", request.url().toString())
            it.proceed(
                request
            )
        }.build())
        // we need to add converter factory to
        // convert JSON object to Java object
        .build()

}