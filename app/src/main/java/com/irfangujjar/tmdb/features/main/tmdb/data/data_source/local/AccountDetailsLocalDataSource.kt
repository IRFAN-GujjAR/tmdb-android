package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface AccountDetailsLocalDataSource {
    suspend fun saveAccountDetails(
        userId: Int,
        username: String,
        avatarPath: String?
    )

    suspend fun watchAccountDetails(): Flow<AccountDetailsEntity?>
}

class AccountDetailsLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : AccountDetailsLocalDataSource {
    private val userIdKey = intPreferencesKey(DataStoreUtil.PreferenceKeys.USER_ID)
    private val usernameKey = stringPreferencesKey(DataStoreUtil.PreferenceKeys.USER_NAME)
    private val avatarUrl = stringPreferencesKey(DataStoreUtil.PreferenceKeys.ACCOUNT_AVATAR_URL)


    override suspend fun saveAccountDetails(
        userId: Int,
        username: String,
        avatarPath: String?
    ) {
        dataStore.edit { pref ->
            pref[userIdKey] = userId
            pref[usernameKey] = username
            if (avatarPath != null) {
                pref[avatarUrl] = avatarPath
            }
        }
    }

    override suspend fun watchAccountDetails(): Flow<AccountDetailsEntity?> = dataStore.data
        .map { pref ->
            if (pref.contains(userIdKey) && pref.contains(usernameKey)) {
                AccountDetailsEntity(
                    id = pref[userIdKey]!!,
                    username = pref[usernameKey]!!,
                    avatarPath = pref[avatarUrl]
                )
            } else {
                null
            }
        }

}

