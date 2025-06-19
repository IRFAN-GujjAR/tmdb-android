package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.states

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.TvShowCreditsModel

sealed interface TvShowCreditsState {
    data object Initializing : TvShowCreditsState
    data class Initialized(val credits: TvShowCreditsModel) : TvShowCreditsState
}