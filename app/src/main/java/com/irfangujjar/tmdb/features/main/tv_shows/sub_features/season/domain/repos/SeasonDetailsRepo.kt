package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.repos

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel

interface SeasonDetailsRepo {
    suspend fun load(tvId: Int, seasonNo: Int): SeasonDetailsModel
}
