package com.irfangujjar.tmdb.features.app_startup.domain.repos

import com.irfangujjar.tmdb.features.app_startup.domain.entities.AppStartupEntity

interface AppStartupRepo {
    suspend fun loadAppStartupData(): AppStartupEntity
    suspend fun saveAppStartedFirstTime()
}