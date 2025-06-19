package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListApi
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface TMDBMediaListDataSource {
    suspend fun loadMoviesAndTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        sessionId: String,
        sortBy: SortBy
    ): TMDBMediaListMoviesAndTvShowsModel

    suspend fun loadMovies(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): MoviesListModel

    suspend fun loadTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): TvShowsListModel
}


class TMDBMediaListDataSourceImpl(
    private val api: TMDBMediaListApi
) : TMDBMediaListDataSource {
    override suspend fun loadMoviesAndTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        sessionId: String,
        sortBy: SortBy
    ): TMDBMediaListMoviesAndTvShowsModel {
        val moviesList = api.loadMediaListMovies(
            userId = userId,
            category = category.categoryValue,
            sessionId = sessionId,
            sortBy = sortBy.title,
            pageNo = 1
        )
        val tvShowsList = api.loadMediaListTvShows(
            userId = userId,
            category = category.categoryValue,
            sessionId = sessionId,
            sortBy = sortBy.title,
            pageNo = 1
        )
        return TMDBMediaListMoviesAndTvShowsModel(
            moviesList = moviesList,
            tvShowsList = tvShowsList
        )
    }

    override suspend fun loadMovies(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): MoviesListModel = api.loadMediaListMovies(
        userId = userId,
        category = category.categoryValue,
        sessionId = sessionId,
        sortBy = sortBy.title,
        pageNo = pageNo
    )

    override suspend fun loadTvShows(
        userId: Int,
        category: TMDBMediaListCategory,
        pageNo: Int,
        sessionId: String,
        sortBy: SortBy
    ): TvShowsListModel = api.loadMediaListTvShows(
        userId = userId,
        category = category.categoryValue,
        sessionId = sessionId,
        sortBy = sortBy.title,
        pageNo = pageNo
    )

}