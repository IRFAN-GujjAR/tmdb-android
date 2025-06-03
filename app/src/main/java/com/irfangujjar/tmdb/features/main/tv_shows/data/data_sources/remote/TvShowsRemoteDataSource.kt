package com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api.TvShowApi
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel

interface TvShowsRemoteDataSource {
    suspend fun loadTvShows(): TvShowsModel
}

class TvShowsRemoteDataSourceImpl(
    private val api: TvShowApi
) : TvShowsRemoteDataSource {
    override suspend fun loadTvShows(): TvShowsModel {
        val airingToday = api.airingTodayTvShows(pageNo = 1)
        val trending = api.trendingTvShows(pageNo = 1)
        val topRated = api.topRatedTvShows(pageNo = 1)
        val popular = api.popularTvShows(pageNo = 1)

        return TvShowsModel(
            airingToday = airingToday,
            trending = trending,
            topRated = topRated,
            popular = popular
        )
    }

}