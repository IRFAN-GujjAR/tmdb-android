package com.irfangujjar.tmdb.features.main.tv_shows.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class TvShowsListModel(
    @SerializedName(JsonKeyNames.PAGE_NO) val pageNo: Int,
    @SerializedName(JsonKeyNames.TOTAL_PAGES) val totalPages: Int,
    @SerializedName(JsonKeyNames.RESULTS) val tvShows: List<TvShowModel>
) {
    companion object {
        fun dummyData(): TvShowsListModel = TvShowsListModel(
            pageNo = 1,
            totalPages = 10,
            tvShows = List(20) { TvShowModel.dummyData() }
        )
    }
}