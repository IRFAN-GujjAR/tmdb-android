package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources.api.TvShowDetailsApi
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel

interface TvShowDetailsDataSource {
    suspend fun loadDetails(tvId: Int): TvShowDetailsModel
}

class TvShowDetailsDataSourceImpl(
    private val api: TvShowDetailsApi
) : TvShowDetailsDataSource {
    override suspend fun loadDetails(tvId: Int): TvShowDetailsModel = api.loadDetails(tvId = tvId)
}