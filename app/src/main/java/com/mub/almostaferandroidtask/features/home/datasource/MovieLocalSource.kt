package com.mub.almostaferandroidtask.features.home.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mub.almostaferandroidtask.features.home.models.Movie


@Dao
interface MovieLocalSource {
    @Query("SELECT * FROM movies WHERE sorted_by LIKE :sortedBy ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPagedList(limit: Int, offset: Int, sortedBy: String): List<Movie>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: Movie)

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun getMovieById(id: Int): Movie?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(movie: Movie)
}