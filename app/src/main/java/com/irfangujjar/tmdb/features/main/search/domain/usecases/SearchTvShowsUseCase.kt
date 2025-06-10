package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

class SearchTvShowsUseCase(
    private val repo: SearchRepo
) : UseCase<TvShowsListModel, SearchUseCaseListParams> {
    override suspend fun invoke(params: SearchUseCaseListParams): TvShowsListModel =
        repo.searchTvShows(query = params.query, pageNo = params.pageNo)
}