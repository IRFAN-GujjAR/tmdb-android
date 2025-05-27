package com.irfangujjar.tmdb.features.theme.data.repository

import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.theme.data.data_sources.local.UserThemeLocalDataSource
import com.irfangujjar.tmdb.features.theme.domain.repository.UserThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserThemeRepositoryImpl(
    private val localDS: UserThemeLocalDataSource
) : UserThemeRepository {
    override suspend fun saveUserTheme(theme: UserTheme) = localDS.saveUserTheme(theme.id)

    override suspend fun watchUserTheme(): Flow<UserTheme> {
        val themeId = localDS.watchUserTheme()
        return themeId.map { themeId ->
            if (themeId != null) {
                UserTheme.fromId(themeId)
            } else {
                UserTheme.SYSTEM
            }
        }

    }
}