package com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.main.tv_shows.domain.repositories.TvShowsRepository

class TvShowsUseCaseLoad(
    private val repo: TvShowsRepository
) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() = repo.loadTvShows()
}