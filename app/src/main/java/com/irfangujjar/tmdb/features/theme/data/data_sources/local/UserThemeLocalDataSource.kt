package com.irfangujjar.tmdb.features.theme.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserThemeLocalDataSource {
    suspend fun saveUserTheme(theme: Int)
    suspend fun watchUserTheme(): Flow<Int?>
}

class UserThemeLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : UserThemeLocalDataSource {

    override suspend fun saveUserTheme(theme: Int) {
        dataStore.edit { pref ->
            pref[DataStoreUtil.PreferenceKeys.THEME] = theme
        }
    }

    override suspend fun watchUserTheme(): Flow<Int?> {
        return dataStore.data.map { pref ->
            pref[DataStoreUtil.PreferenceKeys.THEME]
        }
    }
}