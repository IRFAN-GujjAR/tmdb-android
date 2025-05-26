package com.irfangujjar.tmdb.features.login.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName("request_token")
    val requestToken: String
)
