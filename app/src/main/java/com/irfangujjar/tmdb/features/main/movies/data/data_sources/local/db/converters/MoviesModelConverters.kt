package com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.converters

import androidx.room.TypeConverter
import com.irfangujjar.tmdb.core.di.GsonProvider
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

object MoviesModelConverters {

    @TypeConverter
    fun fromMoviesListModel(moviesList: MoviesListModel?): String? =
        GsonProvider.gson.toJson(moviesList)


    @TypeConverter
    fun toMoviesListModel(json: String?): MoviesListModel? =
        GsonProvider.gson.fromJson(json, MoviesListModel::class.java)

}