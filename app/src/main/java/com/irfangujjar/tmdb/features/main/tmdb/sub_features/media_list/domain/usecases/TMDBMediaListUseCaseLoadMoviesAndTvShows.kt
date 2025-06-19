package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos.TMDBMediaListRepo
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadMoviesAndTvShowsParams


class TMDBMediaListUseCaseLoadMoviesAndTvShows(
    private val repo: TMDBMediaListRepo
) :
    UseCase<TMDBMediaListMoviesAndTvShowsModel, TMDBMediaListUseCaseLoadMoviesAndTvShowsParams> {
    override suspend fun invoke(params: TMDBMediaListUseCaseLoadMoviesAndTvShowsParams): TMDBMediaListMoviesAndTvShowsModel =
        repo.loadMoviesAndTvShows(
            userId = params.userId,
            category = params.category,
            sessionId = params.sessionId,
            sortBy = params.sortBy
        )
}