package com.irfangujjar.tmdb.features.main.movies.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.main.movies.domain.repos.MoviesRepo

class MoviesUseCaseLoad(private val repo: MoviesRepo) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() = repo.loadMovies()
}