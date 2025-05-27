package com.irfangujjar.tmdb.features.app_startup.data.repositories

import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.app_startup.data.data_source.local.AppStartupLocalDataSource
import com.irfangujjar.tmdb.features.app_startup.domain.entities.AppStartupEntity
import com.irfangujjar.tmdb.features.app_startup.domain.repositories.AppStartupRepository

class AppStartupRepositoryImpl(
    private val localDS: AppStartupLocalDataSource
) : AppStartupRepository {
    override suspend fun loadAppStartupData(): AppStartupEntity {
        val appStartupData = localDS.loadAppStartupData()
        var userTheme = UserTheme.SYSTEM
        if (appStartupData.userTheme != null) {
            userTheme = UserTheme.fromId(appStartupData.userId!!)
        }
        return AppStartupEntity(
            isAppStartedFirstTime = appStartupData.isAppStartedFirstTime,
            userTheme = userTheme,
            userId = appStartupData.userId,
            sessionId = appStartupData.sessionId
        )
    }

    override suspend fun saveAppStartedFirstTime() =
        localDS.saveAppStartedFirstTime()
}