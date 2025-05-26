package com.irfangujjar.tmdb.features.login.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity
import com.irfangujjar.tmdb.features.login.domain.repository.LoginRepository
import com.irfangujjar.tmdb.features.login.domain.usecase.params.LoginParams

class LoginUseCase(private val repo: LoginRepository) : UseCase<SessionEntity, LoginParams> {

    override suspend fun invoke(params: LoginParams): SessionEntity = repo.login(
        params.username,
        params.password
    )
}