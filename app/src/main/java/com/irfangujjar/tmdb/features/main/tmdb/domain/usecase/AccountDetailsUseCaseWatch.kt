package com.irfangujjar.tmdb.features.main.tmdb.domain.usecase

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repository.AccountDetailsRepository
import kotlinx.coroutines.flow.Flow

class AccountDetailsUseCaseWatch(
    private val repo: AccountDetailsRepository
) : UseCaseWithoutParams<Flow<AccountDetailsWithoutIdEntity?>> {
    override suspend fun invoke(): Flow<AccountDetailsWithoutIdEntity?> = repo.watchAccountDetails()
}