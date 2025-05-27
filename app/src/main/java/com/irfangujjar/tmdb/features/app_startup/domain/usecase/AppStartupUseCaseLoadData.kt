package com.irfangujjar.tmdb.features.app_startup.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.app_startup.domain.entities.AppStartupEntity
import com.irfangujjar.tmdb.features.app_startup.domain.repositories.AppStartupRepository

class AppStartupUseCaseLoadData(
    private val repo: AppStartupRepository
) : UseCaseWithoutParams<AppStartupEntity> {
    override suspend fun invoke(): AppStartupEntity = repo.loadAppStartupData()
}