package com.irfangujjar.tmdb.features.main.tv_shows.data.repos

import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.TvShowsLocalDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.TvShowsRemoteDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.repos.TvShowsRepo
import kotlinx.coroutines.flow.Flow

class TvShowsRepoImpl(
    private val localDS: TvShowsLocalDataSource,
    private val remoteDS: TvShowsRemoteDataSource
) : TvShowsRepo {
    override fun getTvShowsFlow(): Flow<TvShowsModel?> = localDS.getTvShowsFlow()

    override suspend fun loadTvShows() {
        val tvShows = remoteDS.loadTvShows()
        localDS.insertTvShows(tvShows = tvShows)
    }
}