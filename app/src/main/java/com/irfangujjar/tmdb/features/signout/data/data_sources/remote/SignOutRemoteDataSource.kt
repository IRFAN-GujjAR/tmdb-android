package com.irfangujjar.tmdb.features.signout.data.data_sources.remote

import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api.SignOutApi
import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api.dto.SignOutBodyParam
import com.irfangujjar.tmdb.features.signout.domain.models.SignOutModel

interface SignOutRemoteDataSource {
    suspend fun signOut(sessionId: String): SignOutModel
}

class SignOutRemoteDataSourceImpl(
    private val api: SignOutApi
) : SignOutRemoteDataSource {
    override suspend fun signOut(sessionId: String): SignOutModel = api.signOut(
        bodyParam = SignOutBodyParam(
            sessionId = sessionId
        )
    )
}