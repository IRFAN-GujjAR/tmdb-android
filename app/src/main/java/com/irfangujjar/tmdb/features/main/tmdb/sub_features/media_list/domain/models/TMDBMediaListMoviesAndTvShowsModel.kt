package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models

import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

data class TMDBMediaListMoviesAndTvShowsModel(
    val moviesList: MoviesListModel,
    val tvShowsList: TvShowsListModel
) {
    fun isEmpty(): Boolean = isMoviesEmpty() && isTvShowsEmpty()

    fun isMoviesEmpty(): Boolean = moviesList.movies.isEmpty()
    fun isTvShowsEmpty(): Boolean = tvShowsList.tvShows.isEmpty()

    fun isBothNotEmpty(): Boolean = !isMoviesEmpty() && !isTvShowsEmpty()
}
