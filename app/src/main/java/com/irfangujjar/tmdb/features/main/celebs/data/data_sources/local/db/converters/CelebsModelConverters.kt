package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.db.converters

import androidx.room.TypeConverter
import com.irfangujjar.tmdb.core.di.GsonProvider
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel

object CelebsModelConverters {

    @TypeConverter
    fun fromCelebsList(celebsList: CelebsListModel?): String? =
        GsonProvider.gson.toJson(celebsList)

    @TypeConverter
    fun toCelebsList(json: String?): CelebsListModel? = GsonProvider.gson.fromJson(
        json,
        CelebsListModel::class.java
    )
}