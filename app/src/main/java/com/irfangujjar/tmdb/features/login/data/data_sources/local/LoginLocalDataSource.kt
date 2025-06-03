package com.irfangujjar.tmdb.features.login.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil

interface LoginLocalDataSource {
    suspend fun saveSessionId(sessionId: String)
}

class LoginLocalDataSourceImpl(
    private val dataSource: DataStore<Preferences>
) : LoginLocalDataSource {


    override suspend fun saveSessionId(sessionId: String) {
        dataSource.edit { pref ->
            pref[DataStoreUtil.PreferenceKeys.SESSION_ID] = sessionId
        }
    }

}