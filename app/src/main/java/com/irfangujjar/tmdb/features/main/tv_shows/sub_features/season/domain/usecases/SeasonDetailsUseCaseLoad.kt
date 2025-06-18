package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.repos.SeasonDetailsRepo
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.usecases.params.SeasonDetailsUseCaseLoadPrams

class SeasonDetailsUseCaseLoad(
    private val repo: SeasonDetailsRepo
) : UseCase<SeasonDetailsModel, SeasonDetailsUseCaseLoadPrams> {
    override suspend fun invoke(params: SeasonDetailsUseCaseLoadPrams): SeasonDetailsModel =
        repo.load(tvId = params.tvId, seasonNo = params.seasonNo)
}