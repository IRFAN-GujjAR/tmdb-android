package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams

class SearchMoviesUseCase(
    private val repo: SearchRepo
) : UseCase<MoviesListModel, SearchUseCaseListParams> {
    override suspend fun invoke(params: SearchUseCaseListParams): MoviesListModel =
        repo.searchMovies(query = params.query, pageNo = params.pageNo)
}