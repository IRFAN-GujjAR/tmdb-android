package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.repos

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel

interface SeeAllCelebsRepo {
    suspend fun loadCelebs(category: CelebsCategory, pageNo: Int): CelebsListModel
}