package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params

import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory

data class TMDBMediaListUseCaseLoadParams(
    val userId: Int,
    val category: TMDBMediaListCategory,
    val pageNo: Int,
    val sessionId: String,
    val sortBy: SortBy
)
