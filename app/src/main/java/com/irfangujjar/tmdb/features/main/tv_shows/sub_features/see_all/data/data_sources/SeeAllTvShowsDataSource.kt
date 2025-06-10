package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.data_sources

import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api.TvShowApi
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface SeeAllTvShowsDataSource {
    suspend fun loadTvShows(
        categories: TvShowsCategories,
        pageNo: Int,
        tvId: Int?
    ): TvShowsListModel
}

class SeeAllTvShowsDataSourceImpl(
    private val api: TvShowApi
) : SeeAllTvShowsDataSource {
    override suspend fun loadTvShows(
        categories: TvShowsCategories,
        pageNo: Int,
        tvId: Int?
    ): TvShowsListModel =
        when (categories) {
            TvShowsCategories.AiringToday -> api.airingTodayTvShows(pageNo = pageNo)
            TvShowsCategories.Trending -> api.trendingTvShows(pageNo = pageNo)
            TvShowsCategories.TopRated -> api.topRatedTvShows(pageNo = pageNo)
            TvShowsCategories.Popular -> api.popularTvShows(pageNo = pageNo)
            TvShowsCategories.DetailsRecommended -> api.recommendedTvShows(
                tvId = tvId!!,
                pageNo = pageNo
            )

            TvShowsCategories.DetailsSimilar -> api.similarTvShows(
                tvId = tvId!!,
                pageNo = pageNo
            )
        }
}