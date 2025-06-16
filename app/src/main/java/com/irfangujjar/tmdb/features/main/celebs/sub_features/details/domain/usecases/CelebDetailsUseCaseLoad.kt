package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.repos.CelebDetailsRepo
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.usecases.params.CelebDetailsUseCaseLoadPrams

class CelebDetailsUseCaseLoad(
    private val repo: CelebDetailsRepo
) : UseCase<CelebDetailsModel, CelebDetailsUseCaseLoadPrams> {
    override suspend fun invoke(params: CelebDetailsUseCaseLoadPrams): CelebDetailsModel =
        repo.loadDetails(celebId = params.celebId)
}