package com.irfangujjar.tmdb.features.main.movies.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutAsyncAndParams
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.movies.domain.repos.MoviesRepo
import kotlinx.coroutines.flow.Flow

class MoviesUseCaseWatch(private val repo: MoviesRepo) :
    UseCaseWithoutAsyncAndParams<Flow<MoviesModel?>> {
    override fun invoke(): Flow<MoviesModel?> = repo.getMoviesFlow()
}