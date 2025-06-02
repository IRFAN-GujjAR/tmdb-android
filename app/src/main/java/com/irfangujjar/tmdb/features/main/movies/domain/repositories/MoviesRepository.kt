package com.irfangujjar.tmdb.features.main.movies.domain.repositories

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    fun getMoviesFlow(): Flow<MoviesModel?>
    suspend fun loadMovies()
}