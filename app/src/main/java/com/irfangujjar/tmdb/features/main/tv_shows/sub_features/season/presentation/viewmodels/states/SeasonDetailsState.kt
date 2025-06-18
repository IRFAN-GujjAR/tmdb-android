package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel

sealed interface SeasonDetailsState {
    data object Loading : SeasonDetailsState
    data class Loaded(val seasonDetails: SeasonDetailsModel) : SeasonDetailsState
    data class Error(val error: ErrorEntity) : SeasonDetailsState
}