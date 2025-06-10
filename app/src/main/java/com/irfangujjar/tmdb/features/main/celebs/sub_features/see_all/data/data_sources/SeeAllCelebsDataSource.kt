package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.data.data_sources

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api.CelebApi
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel

interface SeeAllCelebsDataSource {
    suspend fun loadCelebs(category: CelebsCategory, pageNo: Int): CelebsListModel
}

class SeeAllCelebsDataSourceImpl(
    private val api: CelebApi
) : SeeAllCelebsDataSource {
    override suspend fun loadCelebs(
        category: CelebsCategory,
        pageNo: Int
    ): CelebsListModel =
        when (category) {
            CelebsCategory.Popular -> api.popularCelebs(pageNo = pageNo)
            CelebsCategory.Trending -> api.trendingCelebs(pageNo = pageNo)
        }

}