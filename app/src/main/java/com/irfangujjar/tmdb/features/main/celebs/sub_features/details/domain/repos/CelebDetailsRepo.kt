package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.repos

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel

interface CelebDetailsRepo {
    suspend fun loadDetails(celebId: Int): CelebDetailsModel
}