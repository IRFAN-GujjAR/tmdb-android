package com.irfangujjar.tmdb.features.main.movies.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.main.movies.domain.repositories.MoviesRepository

class MoviesUseCaseLoad(private val repo: MoviesRepository) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() = repo.loadMovies()
}