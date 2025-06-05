package com.irfangujjar.tmdb.features.main.movies.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel

sealed class MoviesState {
    object Loading : MoviesState()
    class Loaded(val movies: MoviesModel) : MoviesState()
    class ErrorWithCache(val movies: MoviesModel, val error: ErrorEntity) : MoviesState()
    class Error(val error: ErrorEntity) : MoviesState()
}