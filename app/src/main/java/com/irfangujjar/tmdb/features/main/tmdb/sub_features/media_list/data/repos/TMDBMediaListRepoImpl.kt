package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.repos

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.TMDBMediaListDataSource
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos.TMDBMediaListRepo
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

class TMDBMediaListRepoImpl(
    private val dataSource: TMDBMediaListDataSource
) : TMDBMediaListRepo {
    override suspend fun loadMoviesAndTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        sessionId: String,
        sortBy: SortBy
    ): TMDBMediaListMoviesAndTvShowsModel =
        dataSource.loadMoviesAndTvShows(
            userId = userId,
            category = category,
            sessionId = sessionId,
            sortBy = sortBy
        )

    override suspend fun loadMovies(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): MoviesListModel = dataSource.loadMovies(
        userId = userId,
        category = category,
        pageNo = pageNo,
        sessionId = sessionId,
        sortBy = sortBy
    )

    override suspend fun loadTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): TvShowsListModel = dataSource.loadTvShows(
        userId = userId,
        category = category,
        pageNo = pageNo,
        sessionId = sessionId,
        sortBy = sortBy
    )

}