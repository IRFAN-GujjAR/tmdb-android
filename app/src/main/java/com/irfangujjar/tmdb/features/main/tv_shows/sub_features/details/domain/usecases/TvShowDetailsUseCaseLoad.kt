package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.repos.TvShowDetailsRepo
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.usecases.params.TvShowDetailsUseCaseLoadPrams

class TvShowDetailsUseCaseLoad(
    private val repo: TvShowDetailsRepo
) : UseCase<TvShowDetailsModel, TvShowDetailsUseCaseLoadPrams> {
    override suspend fun invoke(params: TvShowDetailsUseCaseLoadPrams): TvShowDetailsModel =
        repo.loadDetails(tvId = params.tvId)
}