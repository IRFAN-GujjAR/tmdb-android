package com.irfangujjar.tmdb.features.main.movies.data.repos

import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.MoviesLocalDataSource
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.MoviesRemoteDataSource
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.movies.domain.repos.MoviesRepo
import kotlinx.coroutines.flow.Flow

class MoviesRepoImpl(
    private val localDS: MoviesLocalDataSource,
    private val remoteDS: MoviesRemoteDataSource,
) : MoviesRepo {
    override fun getMoviesFlow(): Flow<MoviesModel?> = localDS.getMoviesFlow()

    override suspend fun loadMovies() {
        val movies = remoteDS.loadMovies()
        localDS.insertMovies(movies = movies)
    }
}