package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.repos.SeeAllMoviesRepo
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.params.SeeAllMoviesUseCaseLoadParams

class SeeAllMoviesUseCaseLoad(
    private val repo: SeeAllMoviesRepo
) : UseCase<MoviesListModel, SeeAllMoviesUseCaseLoadParams> {
    override suspend fun invoke(params: SeeAllMoviesUseCaseLoadParams): MoviesListModel =
        repo.loadMovies(
            category = params.category, movieId = params.movieId,
            pageNo = params.pageNo
        )
}