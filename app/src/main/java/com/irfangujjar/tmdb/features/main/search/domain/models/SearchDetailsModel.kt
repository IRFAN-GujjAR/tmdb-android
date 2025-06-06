package com.irfangujjar.tmdb.features.main.search.domain.models

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

data class SearchDetailsModel(
    val moviesList: MoviesListModel,
    val tvShowsList: TvShowsListModel,
    val celebsList: CelebsListModel
)