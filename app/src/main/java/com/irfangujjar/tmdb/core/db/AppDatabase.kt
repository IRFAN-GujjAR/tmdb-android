package com.irfangujjar.tmdb.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.db.converters.CelebsModelConverters
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.db.dao.CelebsDao
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.converters.MoviesModelConverters
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.dao.MoviesDao
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.db.converters.TvShowsModelConverters
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.db.dao.TvShowsDao
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel

@Database(
    entities = [MoviesModel::class, TvShowsModel::class, CelebsModel::class],
    version = 1
)
@TypeConverters(value = [MoviesModelConverters::class, TvShowsModelConverters::class, CelebsModelConverters::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun tvShowsDao(): TvShowsDao
    abstract fun celebsDao(): CelebsDao
}