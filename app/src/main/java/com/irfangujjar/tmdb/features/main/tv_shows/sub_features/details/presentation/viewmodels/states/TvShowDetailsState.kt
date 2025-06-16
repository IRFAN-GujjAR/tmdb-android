package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel

sealed interface TvShowDetailsState {
    data object Loading : TvShowDetailsState
    data class Loaded(
        val tvShowDetails: TvShowDetailsModel
    ) : TvShowDetailsState

    data class Error(
        val error: ErrorEntity
    ) : TvShowDetailsState
}