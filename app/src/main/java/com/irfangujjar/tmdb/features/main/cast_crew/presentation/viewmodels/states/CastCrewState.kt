package com.irfangujjar.tmdb.features.main.cast_crew.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.models.CreditsModel

sealed interface CastCrewState {
    data object Initializing : CastCrewState
    data class Initialized(
        val credits: CreditsModel
    ) : CastCrewState
}