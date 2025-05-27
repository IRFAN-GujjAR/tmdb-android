package com.irfangujjar.tmdb.features.main.tmdb.data.repository

import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local.AccountDetailsLocalDataSource
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local.dto.toEntityWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.AccountDetailsRemoteDataSource
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.dto.toEntity
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.dto.toEntityWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repository.AccountDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountDetailsRepositoryImpl(
    private val remoteDataSource: AccountDetailsRemoteDataSource,
    private val localDataSource: AccountDetailsLocalDataSource
) : AccountDetailsRepository {
    override suspend fun loadDetailsWithoutId(sessionId: String): AccountDetailsWithoutIdEntity {
        val accountDetails = remoteDataSource.loadAccountDetails(sessionId)
        localDataSource.saveAccountDetailsWithoutId(
            username = accountDetails.username,
            profilePath = accountDetails.avatar.tmdb.avatarPath
        )
        return accountDetails.toEntityWithoutId()
    }

    override suspend fun loadDetails(sessionId: String): AccountDetailsEntity {
        val accountDetails = remoteDataSource.loadAccountDetails(sessionId)
        localDataSource.saveAccountDetails(
            userId = accountDetails.id, username = accountDetails.username,
            profilePath = accountDetails.avatar.tmdb.avatarPath
        )
        return accountDetails.toEntity()
    }

    override suspend fun watchAccountDetails(): Flow<AccountDetailsWithoutIdEntity?> =
        localDataSource.watchAccountDetails().map { data -> data?.toEntityWithoutId() }
}