package com.irfangujjar.tmdb.features.app_startup.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.app_startup.domain.repos.AppStartupRepo

class AppStartupUseCaseSave(
    private val repo: AppStartupRepo
) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() =
        repo.saveAppStartedFirstTime()
}