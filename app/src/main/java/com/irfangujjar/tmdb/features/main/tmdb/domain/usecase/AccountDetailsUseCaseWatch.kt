package com.irfangujjar.tmdb.features.main.tmdb.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repos.AccountDetailsRepo
import kotlinx.coroutines.flow.Flow

class AccountDetailsUseCaseWatch(
    private val repo: AccountDetailsRepo
) : UseCaseWithoutParams<Flow<AccountDetailsWithoutIdEntity?>> {
    override suspend fun invoke(): Flow<AccountDetailsWithoutIdEntity?> = repo.watchAccountDetails()
}