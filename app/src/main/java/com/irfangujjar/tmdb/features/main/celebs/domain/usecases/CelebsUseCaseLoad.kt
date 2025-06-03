package com.irfangujjar.tmdb.features.main.celebs.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.main.celebs.domain.repos.CelebsRepo

class CelebsUseCaseLoad(
    private val repo: CelebsRepo
) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() = repo.loadCelebs()
}