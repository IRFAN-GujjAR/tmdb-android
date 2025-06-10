package com.irfangujjar.tmdb.features.main.celebs.domain.repos

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import kotlinx.coroutines.flow.Flow

interface CelebsRepo {
    fun getCelebsFlow(): Flow<CelebsModel?>
    suspend fun loadCelebs()
    suspend fun loadSeeAllCelebs(category: CelebsCategory, pageNo: Int): CelebsListModel
}