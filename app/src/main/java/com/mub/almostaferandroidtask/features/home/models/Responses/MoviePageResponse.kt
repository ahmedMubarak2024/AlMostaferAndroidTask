package com.mub.almostaferandroidtask.features.home.models.Responses


import com.google.gson.annotations.SerializedName

data class MoviePageResponse(
    @SerializedName("page")
    val page: Int? = null,
    @SerializedName("results")
    val results: List<MovieDto>? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    @SerializedName("total_results")
    val totalResults: Int? = null,
    var sortedBy: String
)