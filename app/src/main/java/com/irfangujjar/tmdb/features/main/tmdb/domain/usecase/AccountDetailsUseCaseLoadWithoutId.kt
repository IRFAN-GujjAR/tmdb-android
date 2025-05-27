package com.irfangujjar.tmdb.features.main.tmdb.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repository.AccountDetailsRepository
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.params.AccountDetailsParams

class AccountDetailsUseCaseLoadWithoutId(
    private val repo: AccountDetailsRepository
) : UseCase<AccountDetailsWithoutIdEntity, AccountDetailsParams> {


    override suspend fun invoke(params: AccountDetailsParams): AccountDetailsWithoutIdEntity =
        repo.loadDetailsWithoutId(params.sessionId)
}