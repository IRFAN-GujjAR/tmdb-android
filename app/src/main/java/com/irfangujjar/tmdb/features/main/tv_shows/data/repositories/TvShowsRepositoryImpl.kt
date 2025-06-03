package com.irfangujjar.tmdb.features.main.tv_shows.data.repositories

import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.TvShowsLocalDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.TvShowsRemoteDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.repositories.TvShowsRepository
import kotlinx.coroutines.flow.Flow

class TvShowsRepositoryImpl(
    private val localDS: TvShowsLocalDataSource,
    private val remoteDS: TvShowsRemoteDataSource
) : TvShowsRepository {
    override fun getTvShowsFlow(): Flow<TvShowsModel?> = localDS.getTvShowsFlow()

    override suspend fun loadTvShows() {
        val tvShows = remoteDS.loadTvShows()
        localDS.insertTvShows(tvShows = tvShows)
    }
}