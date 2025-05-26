package com.irfangujjar.tmdb.features.login.domain.repository

import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity

interface LoginRepository {
    suspend fun login(username: String, password: String): SessionEntity
}