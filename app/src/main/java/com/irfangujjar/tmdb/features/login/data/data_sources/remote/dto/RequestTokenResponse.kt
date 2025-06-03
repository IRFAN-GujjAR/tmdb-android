package com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName(JsonKeyNames.REQUEST_TOKEN)
    val requestToken: String
)
