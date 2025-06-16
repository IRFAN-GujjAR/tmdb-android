package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources.api.CelebDetailsApi
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel

interface CelebDetailsDataSource {
    suspend fun loadDetails(celebId: Int): CelebDetailsModel
}

class CelebDetailsDataSourceImpl(
    private val api: CelebDetailsApi
) : CelebDetailsDataSource {
    override suspend fun loadDetails(celebId: Int): CelebDetailsModel =
        api.loadDetails(celebId = celebId)
}