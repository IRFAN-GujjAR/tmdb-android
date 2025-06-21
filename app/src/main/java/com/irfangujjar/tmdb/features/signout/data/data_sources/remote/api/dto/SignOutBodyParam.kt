package com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class SignOutBodyParam(
    @SerializedName(JsonKeyNames.SESSION_ID)
    val sessionId: String
)