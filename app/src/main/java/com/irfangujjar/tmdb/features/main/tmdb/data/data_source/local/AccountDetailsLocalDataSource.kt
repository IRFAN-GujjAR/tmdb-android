package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local.dto.AccountDetailsWithoutIdDSModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


interface AccountDetailsLocalDataSource {
    suspend fun saveAccountDetails(
        userId: Int,
        username: String,
        profilePath: String?
    )

    suspend fun saveAccountDetailsWithoutId(
        username: String,
        profilePath: String?
    )

    suspend fun watchAccountDetails(): Flow<AccountDetailsWithoutIdDSModel?>
}

class AccountDetailsLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>
) : AccountDetailsLocalDataSource {


    override suspend fun saveAccountDetails(
        userId: Int,
        username: String,
        profilePath: String?
    ) {
        dataStore.edit { pref ->
            pref[DataStoreUtil.PreferenceKeys.USER_ID] = userId
            pref[DataStoreUtil.PreferenceKeys.USER_NAME] = username
            if (profilePath != null) {
                pref[DataStoreUtil.PreferenceKeys.ACCOUNT_AVATAR_URL] = profilePath
            }
        }
    }

    override suspend fun saveAccountDetailsWithoutId(
        username: String,
        profilePath: String?
    ) {
        dataStore.edit { pref ->
            pref[DataStoreUtil.PreferenceKeys.USER_NAME] = username
            if (profilePath != null) {
                pref[DataStoreUtil.PreferenceKeys.ACCOUNT_AVATAR_URL] = profilePath
            }
        }
    }

    override suspend fun watchAccountDetails(): Flow<AccountDetailsWithoutIdDSModel?> =
        dataStore.data
            .map { pref ->
                if (pref.contains(DataStoreUtil.PreferenceKeys.USER_NAME)) {
                    AccountDetailsWithoutIdDSModel(
                        username = pref[DataStoreUtil.PreferenceKeys.USER_NAME]!!,
                        profilePath = pref[DataStoreUtil.PreferenceKeys.ACCOUNT_AVATAR_URL]
                    )
                } else {
                    null
                }
            }

}

