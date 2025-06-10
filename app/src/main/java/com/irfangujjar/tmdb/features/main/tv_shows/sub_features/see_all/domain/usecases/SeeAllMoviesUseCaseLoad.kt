package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.repos.SeeAllTvShowsRepo
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.params.SeeAllTvShowsUseCaseLoadParams

class SeeAllTvShowsUseCaseLoad(
    private val repo: SeeAllTvShowsRepo
) : UseCase<TvShowsListModel, SeeAllTvShowsUseCaseLoadParams> {
    override suspend fun invoke(params: SeeAllTvShowsUseCaseLoadParams): TvShowsListModel =
        repo.loadTvShows(
            category = params.category, tvId = params.tvId,
            pageNo = params.pageNo
        )
}