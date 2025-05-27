package com.irfangujjar.tmdb.features.app_startup.domain.repositories

import com.irfangujjar.tmdb.features.app_startup.domain.entities.AppStartupEntity

interface AppStartupRepository {
    suspend fun loadAppStartupData(): AppStartupEntity
    suspend fun saveAppStartedFirstTime()
}