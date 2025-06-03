package com.irfangujjar.tmdb.features.app_startup.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.app_startup.domain.entities.AppStartupEntity
import com.irfangujjar.tmdb.features.app_startup.domain.repos.AppStartupRepo

class AppStartupUseCaseLoadData(
    private val repo: AppStartupRepo
) : UseCaseWithoutParams<AppStartupEntity> {
    override suspend fun invoke(): AppStartupEntity = repo.loadAppStartupData()
}