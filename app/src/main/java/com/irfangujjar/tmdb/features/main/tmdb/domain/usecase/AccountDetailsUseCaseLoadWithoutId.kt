package com.irfangujjar.tmdb.features.main.tmdb.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repos.AccountDetailsRepo
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.params.AccountDetailsParams

class AccountDetailsUseCaseLoadWithoutId(
    private val repo: AccountDetailsRepo
) : UseCase<AccountDetailsWithoutIdEntity, AccountDetailsParams> {


    override suspend fun invoke(params: AccountDetailsParams): AccountDetailsWithoutIdEntity =
        repo.loadDetailsWithoutId(params.sessionId)
}