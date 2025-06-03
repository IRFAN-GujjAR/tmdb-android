package com.irfangujjar.tmdb.features.main.tv_shows.domain.repos

import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import kotlinx.coroutines.flow.Flow

interface TvShowsRepo {
    fun getTvShowsFlow(): Flow<TvShowsModel?>
    suspend fun loadTvShows()
}