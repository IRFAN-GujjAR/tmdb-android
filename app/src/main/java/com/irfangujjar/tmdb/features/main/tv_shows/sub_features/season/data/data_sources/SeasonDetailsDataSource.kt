package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources.api.SeasonDetailsApi
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel

interface SeasonDetailsDataSource {
    suspend fun load(tvId: Int, seasonNo: Int): SeasonDetailsModel
}

class SeasonDetailsDataSourceImpl(
    private val api: SeasonDetailsApi
) : SeasonDetailsDataSource {
    override suspend fun load(
        tvId: Int,
        seasonNo: Int
    ): SeasonDetailsModel = api.load(tvId = tvId, seasonNo = seasonNo)

}