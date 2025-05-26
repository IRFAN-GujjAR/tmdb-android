package com.irfangujjar.tmdb.features.login.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity

data class SessionResponse(
    @SerializedName("session_id") val sessionId: String
)

fun SessionResponse.toEntity(): SessionEntity {
    return SessionEntity(sessionId = this.sessionId)
}