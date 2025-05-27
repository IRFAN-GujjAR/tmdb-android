package com.irfangujjar.tmdb.features.app_startup.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.app_startup.domain.repositories.AppStartupRepository

class AppStartupUseCaseSave(
    private val repo: AppStartupRepository
) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() =
        repo.saveAppStartedFirstTime()
}