package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.api

import com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.dto.AccountDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface AccountDetailsApi {
    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionId: String
    ): AccountDetailsResponse
}