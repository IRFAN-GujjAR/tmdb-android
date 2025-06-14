package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel

sealed interface MovieDetailsState {
    data object Loading : MovieDetailsState
    data class Loaded(
        val movieDetails: MovieDetailsModel
    ) : MovieDetailsState

    data class Error(
        val error: ErrorEntity
    ) : MovieDetailsState
}