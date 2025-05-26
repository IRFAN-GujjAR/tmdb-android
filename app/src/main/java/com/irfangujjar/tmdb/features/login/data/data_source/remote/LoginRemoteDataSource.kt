package com.irfangujjar.tmdb.features.login.data.data_source.remote

import com.irfangujjar.tmdb.features.login.data.data_source.remote.api.LoginApi
import com.irfangujjar.tmdb.features.login.data.data_source.remote.dto.SessionResponse
import com.irfangujjar.tmdb.features.login.data.data_source.remote.dto.VerifyRequestTokenBody

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