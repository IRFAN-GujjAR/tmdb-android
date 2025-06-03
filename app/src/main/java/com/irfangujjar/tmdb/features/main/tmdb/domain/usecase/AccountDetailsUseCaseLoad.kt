package com.irfangujjar.tmdb.features.main.tmdb.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repos.AccountDetailsRepo
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.params.AccountDetailsParams

class AccountDetailsUseCaseLoad(
    private val repo: AccountDetailsRepo
) : UseCase<AccountDetailsEntity, AccountDetailsParams> {


    override suspend fun invoke(params: AccountDetailsParams): AccountDetailsEntity =
        repo.loadDetails(params.sessionId)
}