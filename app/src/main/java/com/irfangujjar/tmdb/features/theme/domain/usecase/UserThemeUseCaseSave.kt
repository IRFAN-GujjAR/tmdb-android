package com.irfangujjar.tmdb.features.theme.domain.usecase

import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutReturnType
import com.irfangujjar.tmdb.features.theme.domain.repository.UserThemeRepository

class UserThemeUseCaseSave(
    private val repo: UserThemeRepository
) : UseCaseWithoutReturnType<UserTheme> {
    override suspend fun invoke(params: UserTheme) = repo.saveUserTheme(params)
}