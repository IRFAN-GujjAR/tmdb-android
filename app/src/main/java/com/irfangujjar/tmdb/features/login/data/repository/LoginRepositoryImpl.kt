package com.irfangujjar.tmdb.features.login.data.repository

import com.irfangujjar.tmdb.features.login.data.data_source.local.LoginLocalDataSource
import com.irfangujjar.tmdb.features.login.data.data_source.remote.LoginRemoteDataSource
import com.irfangujjar.tmdb.features.login.data.data_source.remote.dto.toEntity
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity
import com.irfangujjar.tmdb.features.login.domain.repository.LoginRepository


class LoginRepositoryImpl(
    private val remoteDataSource: LoginRemoteDataSource,
    private val localDataSource: LoginLocalDataSource,
) : LoginRepository {
    override suspend fun login(username: String, password: String): SessionEntity {
        val session = remoteDataSource.login(username, password)
        localDataSource.saveSessionId(session.sessionId)
        return session.toEntity()
    }

}