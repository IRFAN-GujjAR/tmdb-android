package com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.repos.TvShowsRepo
import kotlinx.coroutines.flow.Flow

class TvShowsUseCaseWatch(private val repo: TvShowsRepo) :
    UseCaseWithoutParams<Flow<TvShowsModel?>> {
    override suspend fun invoke(): Flow<TvShowsModel?> = repo.getTvShowsFlow()
}