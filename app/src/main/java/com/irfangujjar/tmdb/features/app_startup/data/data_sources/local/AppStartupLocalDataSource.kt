package com.irfangujjar.tmdb.features.app_startup.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil
import com.irfangujjar.tmdb.features.app_startup.data.data_sources.local.dto.AppStartupModel
import kotlinx.coroutines.flow.first

interface AppStartupLocalDataSource {
    suspend fun loadAppStartupData(): AppStartupModel
    suspend fun saveAppStartedFirstTime()
}

class AppStartupLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : AppStartupLocalDataSource {


    override suspend fun loadAppStartupData(): AppStartupModel {
        val pref = dataStore.data.first()
        val isAppStartedFirstTime =
            pref[DataStoreUtil.PreferenceKeys.APP_STARTED_FIRST_TIME] != false
        val userTheme = pref[DataStoreUtil.PreferenceKeys.THEME]
        val userId = pref[DataStoreUtil.PreferenceKeys.USER_ID]
        val sessionId = pref[DataStoreUtil.PreferenceKeys.SESSION_ID]

        return AppStartupModel(
            isAppStartedFirstTime = isAppStartedFirstTime,
            userTheme = userTheme,
            userId = userId,
            sessionId = sessionId
        )
    }

    override suspend fun saveAppStartedFirstTime() {
        dataStore.edit { pref ->
            pref[DataStoreUtil.PreferenceKeys.APP_STARTED_FIRST_TIME] = false
        }
    }
}

