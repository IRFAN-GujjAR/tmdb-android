package com.irfangujjar.tmdb.features.main.tv_shows.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel

sealed class TvShowsState {
    object Loading : TvShowsState()
    class Loaded(val tvShows: TvShowsModel) : TvShowsState()
    class ErrorWithCache(val tvShows: TvShowsModel, val error: ErrorEntity) : TvShowsState()
    class Error(val error: ErrorEntity) : TvShowsState()
}