package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.states

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.MovieCreditsModel

sealed interface MovieCreditsState {
    data object Initializing : MovieCreditsState
    data class Initialized(val credits: MovieCreditsModel) : MovieCreditsState
}