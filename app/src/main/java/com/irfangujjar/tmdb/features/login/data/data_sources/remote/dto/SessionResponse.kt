package com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity

data class SessionResponse(
    @SerializedName(JsonKeyNames.SESSION_ID) val sessionId: String
)

fun SessionResponse.toEntity(): SessionEntity {
    return SessionEntity(sessionId = this.sessionId)
}