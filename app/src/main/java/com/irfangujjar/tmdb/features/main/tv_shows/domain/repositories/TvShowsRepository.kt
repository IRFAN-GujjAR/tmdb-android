package com.irfangujjar.tmdb.features.main.tv_shows.domain.repositories

import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import kotlinx.coroutines.flow.Flow

interface TvShowsRepository {
    fun getTvShowsFlow(): Flow<TvShowsModel?>
    suspend fun loadTvShows()
}