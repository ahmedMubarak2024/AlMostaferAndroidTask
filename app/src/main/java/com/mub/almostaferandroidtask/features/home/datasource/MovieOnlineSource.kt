package com.mub.almostaferandroidtask.features.home.datasource

import com.mub.almostaferandroidtask.features.home.models.Responses.MoviePageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieOnlineSource {
    @GET("discover/movie")
    suspend fun getMovies(
        @Query("page") pageNum: Int,
        @Query("sort_by") sortedBy: String
    ): MoviePageResponse
}