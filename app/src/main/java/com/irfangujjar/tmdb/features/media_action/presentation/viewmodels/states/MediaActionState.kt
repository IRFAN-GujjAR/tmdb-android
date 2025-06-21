package com.irfangujjar.tmdb.features.media_action.presentation.viewmodels.states

sealed interface MediaActionState {
    data object Idle : MediaActionState
    data object Loading : MediaActionState
    data object Loaded : MediaActionState
    data class Error(val errorMessage: String) : MediaActionState
}