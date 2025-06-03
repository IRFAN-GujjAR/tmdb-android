package com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local

import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.db.dao.TvShowsDao
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import kotlinx.coroutines.flow.Flow

interface TvShowsLocalDataSource {
    suspend fun insertTvShows(tvShows: TvShowsModel)
    fun getTvShowsFlow(): Flow<TvShowsModel?>
}

class TvShowsLocalDataSourceImpl(
    private val dao: TvShowsDao
) : TvShowsLocalDataSource {
    override suspend fun insertTvShows(tvShows: TvShowsModel) = dao.insertTvShows(tvShows)

    override fun getTvShowsFlow(): Flow<TvShowsModel?> = dao.getTvShowsFlow()

}