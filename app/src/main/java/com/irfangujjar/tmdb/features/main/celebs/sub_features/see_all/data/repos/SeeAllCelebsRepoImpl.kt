package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.data.repos

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.data.data_sources.SeeAllCelebsDataSource
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.repos.SeeAllCelebsRepo

class SeeAllCelebsRepoImpl(
    private val dataSource: SeeAllCelebsDataSource
) : SeeAllCelebsRepo {
    override suspend fun loadCelebs(
        category: CelebsCategory,
        pageNo: Int
    ): CelebsListModel =
        dataSource.loadCelebs(category = category, pageNo = pageNo)
}