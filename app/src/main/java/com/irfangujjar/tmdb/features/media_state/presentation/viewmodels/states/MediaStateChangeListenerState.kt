package com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states

sealed interface MediaStateChangeListenerState {
    data object Idle : MediaStateChangeListenerState
    data class MovieRatingUpdated(
        val movieId: Int,
        val rating: Int,
        val isRated: Boolean
    ) : MediaStateChangeListenerState

    data class TvShowRatingUpdated(
        val tvId: Int,
        val rating: Int,
        val isRated: Boolean
    ) : MediaStateChangeListenerState


}