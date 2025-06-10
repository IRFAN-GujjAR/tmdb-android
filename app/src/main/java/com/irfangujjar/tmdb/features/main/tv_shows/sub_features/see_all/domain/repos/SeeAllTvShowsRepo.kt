package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.repos

import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface SeeAllTvShowsRepo {
    suspend fun loadTvShows(
        category: TvShowsCategory,
        tvId: Int?,
        pageNo: Int
    ): TvShowsListModel
}