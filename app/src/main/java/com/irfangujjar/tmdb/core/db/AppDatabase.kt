package com.irfangujjar.tmdb.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.converters.MoviesModelConverters
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.dao.MoviesDao
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel

@Database(entities = [MoviesModel::class], version = 1)
@TypeConverters(value = [MoviesModelConverters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
}