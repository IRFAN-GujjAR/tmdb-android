package com.irfangujjar.tmdb.features.theme.domain.repository

import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import kotlinx.coroutines.flow.Flow

interface UserThemeRepository {
    suspend fun saveUserTheme(theme: UserTheme)
    suspend fun watchUserTheme(): Flow<UserTheme>
}