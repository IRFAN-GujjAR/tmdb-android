package com.irfangujjar.tmdb.features.login.domain.repos

import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity

interface LoginRepo {
    suspend fun login(username: String, password: String): SessionEntity
}