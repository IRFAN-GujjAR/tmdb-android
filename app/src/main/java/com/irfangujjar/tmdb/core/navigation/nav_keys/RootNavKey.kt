package com.irfangujjar.tmdb.core.navigation.nav_keys

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

interface RootNavKey : NavKey {
    @Serializable
    data object LoginNavKey : RootNavKey

    @Serializable
    data object HomeNavKey : RootNavKey
}