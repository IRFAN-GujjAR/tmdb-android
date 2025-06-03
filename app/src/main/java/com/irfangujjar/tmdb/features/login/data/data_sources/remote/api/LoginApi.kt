package com.irfangujjar.tmdb.features.login.data.data_sources.remote.api

import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.RequestTokenResponse
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.SessionResponse
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.VerifyRequestTokenBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginApi {
    @GET("authentication/token/new")
    suspend fun createRequestToken(): RequestTokenResponse

    @POST("authentication/token/validate_with_login")
    suspend fun verifyRequestToken(
        @Body requestTokenBodyModel: VerifyRequestTokenBody
    ): RequestTokenResponse

    @POST("authentication/session/new")
    suspend fun createSessionId(
        @Body requestTokenModel: RequestTokenResponse
    ): SessionResponse
}