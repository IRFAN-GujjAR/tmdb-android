package com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.api.AccountDetailsApi
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.dto.AccountDetailsResponse

interface AccountDetailsRemoteDataSource {
    suspend fun loadAccountDetails(sessionId: String): AccountDetailsResponse
}

class AccountDetailsRemoteDataSourceImpl(
    private val api: AccountDetailsApi
) : AccountDetailsRemoteDataSource {
    override suspend fun loadAccountDetails(sessionId: String): AccountDetailsResponse =
        api.getAccountDetails(sessionId)
}