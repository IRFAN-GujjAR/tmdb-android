package com.irfangujjar.tmdb.features.signout.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutReturnType
import com.irfangujjar.tmdb.features.signout.domain.repos.SignOutRepo
import com.irfangujjar.tmdb.features.signout.domain.usecases.params.SignOutUseCaseParams

class SignOutUseCase(
    private val repo: SignOutRepo
) : UseCaseWithoutReturnType<SignOutUseCaseParams> {
    override suspend fun invoke(params: SignOutUseCaseParams) =
        repo.signOut(sessionId = params.sessionId)
}