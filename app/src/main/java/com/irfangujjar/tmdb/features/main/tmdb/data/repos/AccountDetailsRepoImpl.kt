package com.irfangujjar.tmdb.features.main.tmdb.data.repos

import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.local.AccountDetailsLocalDataSource
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.local.dto.toEntityWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.AccountDetailsRemoteDataSource
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.dto.toEntity
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.dto.toEntityWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.repos.AccountDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AccountDetailsRepoImpl(
    private val remoteDataSource: AccountDetailsRemoteDataSource,
    private val localDataSource: AccountDetailsLocalDataSource
) : AccountDetailsRepo {
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