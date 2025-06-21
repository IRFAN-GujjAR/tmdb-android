package com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel

sealed interface MediaStateState {
    data object Idle : MediaStateState
    data object Loading : MediaStateState
    data class Loaded(val mediaState: MediaStateModel) : MediaStateState
    data class Error(val error: ErrorEntity) : MediaStateState
}