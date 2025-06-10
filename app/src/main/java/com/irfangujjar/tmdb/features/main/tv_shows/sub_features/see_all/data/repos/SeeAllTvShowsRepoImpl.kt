package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.repos

import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.data_sources.SeeAllTvShowsDataSource
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.repos.SeeAllTvShowsRepo

class SeeAllTvShowsRepoImpl(
    private val dataSource: SeeAllTvShowsDataSource
) : SeeAllTvShowsRepo {
    override suspend fun loadTvShows(
        category: TvShowsCategories,
        tvId: Int?,
        pageNo: Int
    ): TvShowsListModel = dataSource.loadTvShows(
        categories = category,
        tvId = tvId,
        pageNo = pageNo
    )
}