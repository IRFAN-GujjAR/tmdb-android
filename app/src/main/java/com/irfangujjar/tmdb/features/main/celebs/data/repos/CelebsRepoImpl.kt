package com.irfangujjar.tmdb.features.main.celebs.data.repos

import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.CelebsLocalDataSource
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.CelebsRemoteDataSource
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import com.irfangujjar.tmdb.features.main.celebs.domain.repos.CelebsRepo
import kotlinx.coroutines.flow.Flow

class CelebsRepoImpl(
    private val localDS: CelebsLocalDataSource,
    private val remoteDS: CelebsRemoteDataSource
) : CelebsRepo {
    override fun getCelebsFlow(): Flow<CelebsModel?> = localDS.getCelebritiesFlow()

    override suspend fun loadCelebs() {
        val celebs = remoteDS.loadCelebs()
        localDS.insertCelebrities(celebrities = celebs)
    }
}