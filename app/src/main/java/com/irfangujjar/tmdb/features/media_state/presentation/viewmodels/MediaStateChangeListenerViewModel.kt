package com.irfangujjar.tmdb.features.media_state.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateChangeListenerState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MediaStateChangeListenerViewModel @Inject constructor() : ViewModel() {

    var state: MediaStateChangeListenerState = MediaStateChangeListenerState.Idle
        private set

    fun updateState(mediaStateType: MediaStateType, mediaId: Int, rating: Int, isRated: Boolean) {
        state = when (mediaStateType) {
            MediaStateType.Movie -> MediaStateChangeListenerState.MovieRatingUpdated(
                movieId = mediaId,
                rating = rating,
                isRated = isRated
            )

            MediaStateType.Tv -> MediaStateChangeListenerState.TvShowRatingUpdated(
                tvId = mediaId,
                rating = rating,
                isRated = isRated
            )
        }
    }

    fun resetState() {
        state = MediaStateChangeListenerState.Idle
    }
}