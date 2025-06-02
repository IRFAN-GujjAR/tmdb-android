package com.irfangujjar.tmdb.features.main.movies.data.repositories

import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.MoviesLocalDataSource
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.MoviesRemoteDataSource
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.movies.domain.repositories.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val localDS: MoviesLocalDataSource,
    private val remoteDS: MoviesRemoteDataSource,
) : MoviesRepository {
    override fun getMoviesFlow(): Flow<MoviesModel?> = localDS.getMoviesFlow()

    override suspend fun loadMovies() {
        val movies = remoteDS.loadMovies()
        localDS.insertMovies(movies = movies)
    }
}