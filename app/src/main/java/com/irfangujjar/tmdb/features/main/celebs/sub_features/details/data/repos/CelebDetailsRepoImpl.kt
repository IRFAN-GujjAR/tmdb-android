package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.repos

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources.CelebDetailsDataSource
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.repos.CelebDetailsRepo

class CelebDetailsRepoImpl(
    private val dataSource: CelebDetailsDataSource
) : CelebDetailsRepo {
    override suspend fun loadDetails(celebId: Int): CelebDetailsModel =
        dataSource.loadDetails(celebId = celebId)
}