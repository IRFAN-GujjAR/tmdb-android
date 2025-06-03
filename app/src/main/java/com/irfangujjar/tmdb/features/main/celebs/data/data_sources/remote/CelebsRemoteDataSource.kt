package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api.CelebApi
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel

interface CelebsRemoteDataSource {
    suspend fun loadCelebs(): CelebsModel
}

class CelebsRemoteDataSourceImpl(
    private val api: CelebApi
) : CelebsRemoteDataSource {
    override suspend fun loadCelebs(): CelebsModel {
        val trendingCelebs = api.trendingCelebs(pageNo = 1)
        val popularCelebs = api.popularCelebs(pageNo = 1)
        return CelebsModel(trending = trendingCelebs, popular = popularCelebs)
    }

}