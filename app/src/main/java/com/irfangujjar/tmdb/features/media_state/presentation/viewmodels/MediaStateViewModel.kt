package com.irfangujjar.tmdb.features.media_state.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel
import com.irfangujjar.tmdb.features.media_state.domain.models.RatedModel
import com.irfangujjar.tmdb.features.media_state.domain.usecases.MediaStateUseCaseLoad
import com.irfangujjar.tmdb.features.media_state.domain.usecases.params.MediaStateUseCaseLoadParams
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateChangeListenerState
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaStateViewModel @Inject constructor(
    private val useCase: MediaStateUseCaseLoad,
    private val userSession: UserSession
) : ViewModel() {

    private val _state = MutableStateFlow<MediaStateState>(MediaStateState.Idle)
    val state: StateFlow<MediaStateState> = _state

    private var isLoggedIn = false

    fun initialize(
        isLoggedIn: Boolean, mediaId: Int, type: MediaStateType,
        state: MediaStateChangeListenerState,
        onResetMediaStateChanges: () -> Unit
    ) {
        if (isLoggedIn) {
            if (!this.isLoggedIn) {
                this.isLoggedIn = true
                load(mediaId = mediaId, type = type)
            }
        } else {
            if (this.isLoggedIn) {
                this.isLoggedIn = false
                _state.value = MediaStateState.Idle
            }
        }
        listenToMediaStateChanges(
            state = state,
            onResetMediaStateChanges = onResetMediaStateChanges,
            type = type,
            mediaId = mediaId
        )
    }

    private fun listenToMediaStateChanges(
        state: MediaStateChangeListenerState, type: MediaStateType,
        mediaId: Int,
        onResetMediaStateChanges: () -> Unit
    ) {
        val (isMatch, rating, isRated, targetId) = when (state) {
            is MediaStateChangeListenerState.MovieRatingUpdated ->
                Quadruple(
                    type == MediaStateType.Movie && state.movieId == mediaId,
                    state.rating,
                    state.isRated,
                    state.movieId
                )

            is MediaStateChangeListenerState.TvShowRatingUpdated ->
                Quadruple(
                    type == MediaStateType.Tv && state.tvId == mediaId,
                    state.rating,
                    state.isRated,
                    state.tvId
                )

            MediaStateChangeListenerState.Idle -> return
        }

        if (!isMatch) return

        val currentState = _state.value
        if (currentState is MediaStateState.Loaded) {
            val updatedMediaState = currentState.mediaState.copy(
                watchlist = if (isRated) false else currentState.mediaState.watchlist,
                rated = RatedModel(value = if (isRated) rating.toDouble() else 0.0)
            )
            _state.value = MediaStateState.Loaded(mediaState = updatedMediaState)
        } else {
            load(mediaId = targetId, type = type)
        }

        onResetMediaStateChanges()

    }

    fun load(mediaId: Int, type: MediaStateType) {
        if (_state.value !is MediaStateState.Loading)
            _state.value = MediaStateState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                useCase.invoke(
                    MediaStateUseCaseLoadParams(
                        type = type,
                        mediaId = mediaId,
                        sessionId = userSession.sessionId!!
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.e(
                        "MediaStateViewModel",
                        "Error Loading MediaState : ${result.errorEntity.message}"
                    )
                    _state.value = MediaStateState.Error(
                        error = result.errorEntity
                    )
                }

                is ResultWrapper.Success<MediaStateModel> ->
                    _state.value = MediaStateState.Loaded(mediaState = result.data)
            }
        }
    }
}

data class Quadruple<A, B, C, D>(val first: A, val second: B, val third: C, val fourth: D)
