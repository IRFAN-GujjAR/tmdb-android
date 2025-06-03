package com.irfangujjar.tmdb.features.login.data.repos

import com.irfangujjar.tmdb.features.login.data.data_sources.local.LoginLocalDataSource
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.LoginRemoteDataSource
import com.irfangujjar.tmdb.features.login.data.data_sources.remote.dto.toEntity
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity
import com.irfangujjar.tmdb.features.login.domain.repos.LoginRepo


class LoginRepoImpl(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource,
) : LoginRepo {
    override suspend fun login(username: String, password: String): SessionEntity {
        val session = remoteDataSource.login(username, password)
        localDataSource.saveSessionId(session.sessionId)
        return session.toEntity()
    }

}