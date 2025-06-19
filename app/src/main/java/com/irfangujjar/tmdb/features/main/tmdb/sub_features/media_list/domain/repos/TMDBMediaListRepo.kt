package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

interface TMDBMediaListRepo {
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