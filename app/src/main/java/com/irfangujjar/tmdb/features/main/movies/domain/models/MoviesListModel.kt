package com.irfangujjar.tmdb.features.main.movies.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class MoviesListModel(
    @SerializedName(JsonKeyNames.PAGE_NO) val pageNo: Int,
    @SerializedName(JsonKeyNames.TOTAL_PAGES) val totalPages: Int,
    @SerializedName(JsonKeyNames.RESULTS) val movies: List<MovieModel>
)