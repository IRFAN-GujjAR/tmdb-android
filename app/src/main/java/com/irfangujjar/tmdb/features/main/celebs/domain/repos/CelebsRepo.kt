package com.irfangujjar.tmdb.features.main.celebs.domain.repos

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import kotlinx.coroutines.flow.Flow

interface CelebsRepo {
    fun getCelebsFlow(): Flow<CelebsModel?>
    suspend fun loadCelebs()
}