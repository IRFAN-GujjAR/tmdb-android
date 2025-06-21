package com.irfangujjar.tmdb.features.signout.data.data_sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil

interface SignOutLocalDataSource {
    suspend fun deleteUserDetails()
}

class SignOutLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : SignOutLocalDataSource {
    override suspend fun deleteUserDetails() {
        dataStore.edit { pref ->
            pref.remove(DataStoreUtil.PreferenceKeys.USER_ID)
            pref.remove(DataStoreUtil.PreferenceKeys.SESSION_ID)
            pref.remove(DataStoreUtil.PreferenceKeys.ACCOUNT_AVATAR_URL)
            pref.remove(DataStoreUtil.PreferenceKeys.USER_NAME)
        }
    }
}