package com.mub.almostaferandroidtask.features.home.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo("title")
    val title: String? = null,
    @ColumnInfo("overview")
    val overview: String? = null,
    @ColumnInfo("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @ColumnInfo("sorted_by")
    var sortedBy: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)