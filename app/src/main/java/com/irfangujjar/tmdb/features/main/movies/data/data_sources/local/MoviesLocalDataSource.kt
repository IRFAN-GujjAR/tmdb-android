package com.irfangujjar.tmdb.features.main.movies.data.data_sources.local

import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.dao.MoviesDao
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesLocalDataSource {
    suspend fun insertMovies(movies: MoviesModel)
    fun getMoviesFlow(): Flow<MoviesModel?>
}

class MoviesLocalDataSourceImpl(
    private val dao: MoviesDao
) : MoviesLocalDataSource {
    override suspend fun insertMovies(movies: MoviesModel) = dao.insertMovies(movies)

    override fun getMoviesFlow(): Flow<MoviesModel?> = dao.getMoviesFlow()

}