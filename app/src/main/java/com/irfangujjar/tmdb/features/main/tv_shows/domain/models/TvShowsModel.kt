package com.irfangujjar.tmdb.features.main.tv_shows.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_shows_table")
data class TvShowsModel(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val airingToday: TvShowsListModel,
    val trending: TvShowsListModel,
    val topRated: TvShowsListModel,
    val popular: TvShowsListModel
)
