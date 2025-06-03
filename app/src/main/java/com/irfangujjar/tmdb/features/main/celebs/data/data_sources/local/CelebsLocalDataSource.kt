package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local

import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.db.dao.CelebsDao
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import kotlinx.coroutines.flow.Flow

interface CelebsLocalDataSource {
    suspend fun insertCelebrities(celebrities: CelebsModel)
    fun getCelebritiesFlow(): Flow<CelebsModel?>
}

class CelebsLocalDataSourceImpl(
    private val dao: CelebsDao
) : CelebsLocalDataSource {
    override suspend fun insertCelebrities(celebrities: CelebsModel) =
        dao.insertCelebrities(celebrities)

    override fun getCelebritiesFlow(): Flow<CelebsModel?> = dao.getCelebritiesFlow()
}