package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote

import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.api.AccountDetailsApi
import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.dto.AccountDetailsResponse

interface AccountDetailsRemoteDataSource {
    suspend fun loadAccountDetails(sessionId: String): AccountDetailsResponse
}

class AccountDetailsRemoteDataSourceImpl(
    private val api: AccountDetailsApi
) : AccountDetailsRemoteDataSource {
    override suspend fun loadAccountDetails(sessionId: String): AccountDetailsResponse =
        api.getAccountDetails(sessionId)
}