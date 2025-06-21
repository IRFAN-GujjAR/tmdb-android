package com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api

import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api.dto.SignOutBodyParam
import com.irfangujjar.tmdb.features.signout.domain.models.SignOutModel
import retrofit2.http.Body
import retrofit2.http.HTTP

interface SignOutApi {
    @HTTP(
        method = "DELETE",
        path = "authentication/session",
        hasBody = true
    )
    suspend fun signOut(@Body bodyParam: SignOutBodyParam): SignOutModel
}