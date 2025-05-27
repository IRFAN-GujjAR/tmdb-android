package com.irfangujjar.tmdb.core.data_store

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object DataStoreUtil {
    const val DATA_STORE_NAME = "tmdb_data_store"

    object PreferenceKeys {
        val APP_STARTED_FIRST_TIME = booleanPreferencesKey("app_started_first_time")
        val THEME = intPreferencesKey("theme")
        val SESSION_ID = stringPreferencesKey("session_id")
        val USER_ID = intPreferencesKey("user_id")
        val USER_NAME = stringPreferencesKey("username")
        val ACCOUNT_AVATAR_URL = stringPreferencesKey("account_avatar_url")
    }

}