package com.irfangujjar.tmdb.features.main.movies.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class MoviesModel(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val popular: MoviesListModel,
    val inTheatres: MoviesListModel,
    val trending: MoviesListModel,
    val topRated: MoviesListModel,
    val upcoming: MoviesListModel
)
