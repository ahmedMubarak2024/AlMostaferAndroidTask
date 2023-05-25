package com.mub.almostaferandroidtask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mub.almostaferandroidtask.features.home.datasource.MovieLocalSource
import com.mub.almostaferandroidtask.features.home.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieLocalSource

    companion object {

        fun getInstance(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                "item_database"
            ).fallbackToDestructiveMigration().build()

        }
    }
}