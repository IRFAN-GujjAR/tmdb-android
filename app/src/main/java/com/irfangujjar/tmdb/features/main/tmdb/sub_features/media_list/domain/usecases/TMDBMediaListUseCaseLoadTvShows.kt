package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos.TMDBMediaListRepo
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

class TMDBMediaListUseCaseLoadTvShows(
    private val repo: TMDBMediaListRepo
) : UseCase<TvShowsListModel, TMDBMediaListUseCaseLoadParams> {
    override suspend fun invoke(params: TMDBMediaListUseCaseLoadParams): TvShowsListModel =
        repo.loadTvShows(
            userId = params.userId,
            category = params.category,
            pageNo = params.pageNo,
            sessionId = params.sessionId,
            sortBy = params.sortBy
        )
}