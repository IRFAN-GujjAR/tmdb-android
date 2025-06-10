package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.data_sources

import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api.TvShowApi
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface SeeAllTvShowsDataSource {
    suspend fun loadTvShows(
        categories: TvShowsCategory,
        pageNo: Int,
        tvId: Int?
    ): TvShowsListModel
}

class SeeAllTvShowsDataSourceImpl(
    private val api: TvShowApi
) : SeeAllTvShowsDataSource {
    override suspend fun loadTvShows(
        categories: TvShowsCategory,
        pageNo: Int,
        tvId: Int?
    ): TvShowsListModel =
        when (categories) {
            TvShowsCategory.AiringToday -> api.airingTodayTvShows(pageNo = pageNo)
            TvShowsCategory.Trending -> api.trendingTvShows(pageNo = pageNo)
            TvShowsCategory.TopRated -> api.topRatedTvShows(pageNo = pageNo)
            TvShowsCategory.Popular -> api.popularTvShows(pageNo = pageNo)
            TvShowsCategory.DetailsRecommended -> api.recommendedTvShows(
                tvId = tvId!!,
                pageNo = pageNo
            )

            TvShowsCategory.DetailsSimilar -> api.similarTvShows(
                tvId = tvId!!,
                pageNo = pageNo
            )
        }
}