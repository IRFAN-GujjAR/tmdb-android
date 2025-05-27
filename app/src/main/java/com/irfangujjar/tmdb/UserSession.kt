package com.irfangujjar.tmdb

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor() {
    var sessionId: String? = null
        private set
    var userId: Int? = null
        private set

    fun isLoggedIn(): Boolean = sessionId != null && userId != null


    fun updateSession(sessionId: String, userId: Int) {
        this.sessionId = sessionId
        this.userId = userId
    }

    fun clearSession() {
        sessionId = null
        userId = null
    }
}