package com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.db.converters

import androidx.room.TypeConverter
import com.irfangujjar.tmdb.core.di.GsonProvider
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

object TvShowsModelConverters {

    @TypeConverter
    fun fromTvShowsListModel(tvShowsList: TvShowsListModel?): String? =
        GsonProvider.gson.toJson(tvShowsList)

    @TypeConverter
    fun toTvShowsListModel(json: String?): TvShowsListModel =
        GsonProvider.gson.fromJson(
            json,
            TvShowsListModel::class.java
        )
}