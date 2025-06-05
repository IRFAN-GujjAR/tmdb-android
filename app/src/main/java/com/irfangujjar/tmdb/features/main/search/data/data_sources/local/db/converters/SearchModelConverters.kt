package com.irfangujjar.tmdb.features.main.search.data.data_sources.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.irfangujjar.tmdb.core.di.GsonProvider
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchItemModel

object SearchModelConverters {

    @TypeConverter
    fun fromSearchItemListModel(search: List<SearchItemModel>?): String? =
        GsonProvider.gson.toJson(search)

    @TypeConverter
    fun toSearchItemListModel(json: String?): List<SearchItemModel>? {
        if (json == null) return null
        val type = object : TypeToken<List<SearchItemModel>>() {}.type
        return GsonProvider.gson.fromJson(json, type)
    }
}