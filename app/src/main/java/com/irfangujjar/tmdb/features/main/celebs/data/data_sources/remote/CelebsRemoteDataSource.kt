package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api.CelebApi
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel

interface CelebsRemoteDataSource {
    suspend fun loadCelebs(): CelebsModel
    suspend fun loadSeeAllCelebs(
        category: CelebsCategory,
        pageNo: Int
    ): CelebsListModel
}

class CelebsRemoteDataSourceImpl(
    private val api: CelebApi
) : CelebsRemoteDataSource {
    override suspend fun loadCelebs(): CelebsModel {
        val trendingCelebs = api.trendingCelebs(pageNo = 1)
        val popularCelebs = api.popularCelebs(pageNo = 1)
        return CelebsModel(trending = trendingCelebs, popular = popularCelebs)
    }

    override suspend fun loadSeeAllCelebs(
        category: CelebsCategory,
        pageNo: Int
    ): CelebsListModel =
        when (category) {
            CelebsCategory.Popular -> api.popularCelebs(pageNo = pageNo)
            CelebsCategory.Trending -> api.trendingCelebs(pageNo = pageNo)
        }
}