package com.irfangujjar.tmdb.features.main.search.domain.models

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

data class SearchDetailsModel(
    val moviesList: MoviesListModel,
    val tvShowsList: TvShowsListModel,
    val celebsList: CelebsListModel
) {
    fun isEmpty(): Boolean = isMoviesEmpty() && isTvShowsEmpty() && isCelebsEmpty()

    fun isMoviesEmpty(): Boolean = moviesList.movies.isEmpty()
    fun isTvShowsEmpty(): Boolean = tvShowsList.tvShows.isEmpty()
    fun isCelebsEmpty(): Boolean = celebsList.celebrities.isEmpty()

    fun isAllPresent(): Boolean = !isMoviesEmpty() && !isTvShowsEmpty() && !isCelebsEmpty()

    fun length(): Int {
        if (isAllPresent()) {
            return 4
        }
        var length = 0
        if (!isMoviesEmpty())
            length++
        if (!isTvShowsEmpty())
            length++
        if (!isCelebsEmpty())
            length++
        return length
    }
}