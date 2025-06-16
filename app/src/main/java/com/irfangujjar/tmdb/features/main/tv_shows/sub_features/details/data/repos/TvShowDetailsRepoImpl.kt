package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.repos

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources.TvShowDetailsDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.repos.TvShowDetailsRepo

class TvShowDetailsRepoImpl(
    private val dataSource: TvShowDetailsDataSource
) : TvShowDetailsRepo {
    override suspend fun loadDetails(tvId: Int): TvShowDetailsModel =
        dataSource.loadDetails(tvId = tvId)
}