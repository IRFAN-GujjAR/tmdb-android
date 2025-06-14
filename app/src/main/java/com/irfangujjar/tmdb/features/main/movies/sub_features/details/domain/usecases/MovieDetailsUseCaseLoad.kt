package com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.repos.MovieDetailsRepo
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.params.MovieDetailsUseCaseLoadParams

class MovieDetailsUseCaseLoad(
    private val repo: MovieDetailsRepo
) : UseCase<MovieDetailsModel, MovieDetailsUseCaseLoadParams> {
    override suspend fun invoke(params: MovieDetailsUseCaseLoadParams): MovieDetailsModel =
        repo.loadMovieDetails(movieId = params.movieId)
}