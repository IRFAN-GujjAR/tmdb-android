package com.irfangujjar.tmdb.features.login.data.data_sources.remote

import com.irfangujjar.tmdb.features.login.data.data_sources.remote.api.LoginApi
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.SessionResponse
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.VerifyRequestTokenBody

interface LoginRemoteDataSource {
    suspend fun login(username: String, password: String): SessionResponse
}

class LoginRemoteDataSourceImpl(
    private val api: LoginApi
) : LoginRemoteDataSource {
    override suspend fun login(
        username: String,
        password: String
    ): SessionResponse {
        val requestToken = api.createRequestToken()
        val verifiedToken = api.verifyRequestToken(
            VerifyRequestTokenBody(
                requestToken = requestToken.requestToken,
                username = username,
                password = password
            )
        )
        return api.createSessionId(verifiedToken)
    }
}