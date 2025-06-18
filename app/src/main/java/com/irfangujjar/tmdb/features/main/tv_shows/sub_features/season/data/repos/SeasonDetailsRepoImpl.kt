package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.repos

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources.SeasonDetailsDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.repos.SeasonDetailsRepo

class SeasonDetailsRepoImpl(
    private val dataSource: SeasonDetailsDataSource
) : SeasonDetailsRepo {
    override suspend fun load(
        tvId: Int,
        seasonNo: Int
    ): SeasonDetailsModel = dataSource.load(tvId = tvId, seasonNo = seasonNo)
}