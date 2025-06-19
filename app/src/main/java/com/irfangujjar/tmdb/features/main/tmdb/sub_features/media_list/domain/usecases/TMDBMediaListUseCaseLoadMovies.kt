package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos.TMDBMediaListRepo
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadParams

class TMDBMediaListUseCaseLoadMovies(
    private val repo: TMDBMediaListRepo
) : UseCase<MoviesListModel, TMDBMediaListUseCaseLoadParams> {
    override suspend fun invoke(params: TMDBMediaListUseCaseLoadParams): MoviesListModel =
        repo.loadMovies(
            userId = params.userId,
            category = params.category,
            pageNo = params.pageNo,
            sessionId = params.sessionId,
            sortBy = params.sortBy
        )
}