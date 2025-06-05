package com.irfangujjar.tmdb.features.main.search.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_search_table")
data class SearchModel(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val searches: List<SearchItemModel>
)