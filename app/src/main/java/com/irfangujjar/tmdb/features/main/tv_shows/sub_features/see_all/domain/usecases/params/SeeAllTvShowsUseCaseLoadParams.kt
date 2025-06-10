package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.params

import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory

data class SeeAllTvShowsUseCaseLoadParams(
    val category: TvShowsCategory,
    val tvId: Int?,
    val pageNo: Int
)
