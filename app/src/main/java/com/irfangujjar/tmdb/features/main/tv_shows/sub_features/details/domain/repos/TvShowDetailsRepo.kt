package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.repos

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel

interface TvShowDetailsRepo {
    suspend fun loadDetails(tvId: Int): TvShowDetailsModel
}