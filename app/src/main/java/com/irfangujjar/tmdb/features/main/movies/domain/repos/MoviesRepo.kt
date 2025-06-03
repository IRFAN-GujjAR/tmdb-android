package com.irfangujjar.tmdb.features.main.movies.domain.repos

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepo {
    fun getMoviesFlow(): Flow<MoviesModel?>
    suspend fun loadMovies()
}