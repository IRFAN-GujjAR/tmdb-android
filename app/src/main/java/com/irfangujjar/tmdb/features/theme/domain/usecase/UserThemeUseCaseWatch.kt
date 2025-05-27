package com.irfangujjar.tmdb.features.theme.domain.usecase

import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.theme.domain.repository.UserThemeRepository
import kotlinx.coroutines.flow.Flow

class UserThemeUseCaseWatch(
    private val repo: UserThemeRepository
) : UseCaseWithoutParams<Flow<UserTheme>> {
    override suspend fun invoke(): Flow<UserTheme> = repo.watchUserTheme()
}