package com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.converters

import androidx.room.TypeConverter
import com.irfangujjar.tmdb.core.di.GsonProvider
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

object MoviesModelConverters {

    @TypeConverter
    fun fromMoviesListModel(response: MoviesListModel?): String? {
        return GsonProvider.gson.toJson(response)
    }

    @TypeConverter
    fun toMoviesListModel(json: String?): MoviesListModel? {
        return GsonProvider.gson.fromJson(json, MoviesListModel::class.java)
    }
}