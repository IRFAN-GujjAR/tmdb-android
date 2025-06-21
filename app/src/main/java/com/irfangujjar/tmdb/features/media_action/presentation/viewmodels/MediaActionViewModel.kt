package com.irfangujjar.tmdb.features.media_action.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusState
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseFavorite
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseWatchlist
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseFavoriteParams
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseWatchlistParam
import com.irfangujjar.tmdb.features.media_action.presentation.viewmodels.states.MediaActionState
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MediaActionViewModel @Inject constructor(
    private val useCaseFavorite: MediaActionUseCaseFavorite,
    private val useCaseWatchlist: MediaActionUseCaseWatchlist,
    private val userSession: UserSession
) : ViewModelWithErrorAlerts() {

    private val _state = MutableStateFlow<MediaActionState>(MediaActionState.Idle)
    val state: StateFlow<MediaActionState> = _state

    var isMediaStateLoading by mutableStateOf(false)
        private set
    var isFavorite by mutableStateOf(false)
        private set
    var isRated by mutableStateOf(false)
        private set
    var rating by mutableDoubleStateOf(0.0)
        private set
    var isWatchlist by mutableStateOf(false)
        private set
    var isSignedIn by mutableStateOf(false)
        private set

    var showNotSignedInDialog by mutableStateOf(false)
        private set
    var showFavoriteRemovalDialog by mutableStateOf(false)
        private set
    var showWatchlistRemovalDialog by mutableStateOf(false)
        private set

    fun showNotSignedInDialog() {
        showNotSignedInDialog = true
    }

    fun hideNotSignedInDialog() {
        showNotSignedInDialog = false
    }

    fun showFavoriteRemovalDialog() {
        showFavoriteRemovalDialog = true
    }

    fun hideFavoriteRemovalDialog() {
        showFavoriteRemovalDialog = false
    }

    fun showWatchlistRemovalDialog() {
        showWatchlistRemovalDialog = true
    }

    fun hideWatchlistRemovalDialog() {
        showWatchlistRemovalDialog = false
    }


    fun updateSignInStatus(signInState: SignInStatusState) {
        isSignedIn = when (signInState) {
            SignInStatusState.SignedIn -> true
            SignInStatusState.SignedOut -> false
        }
    }


    fun updateMediaState(state: MediaStateState) {
        when (state) {
            MediaStateState.Loading -> isMediaStateLoading = true
            MediaStateState.Idle, is MediaStateState.Error, is MediaStateState.Loaded -> {
                if (state is MediaStateState.Loaded) {
                    isFavorite = state.mediaState.favorite
                    isRated = state.mediaState.rated.value != 0.0
                    rating = state.mediaState.rated.value
                    isWatchlist = state.mediaState.watchlist
                }
                isMediaStateLoading = false
            }
        }

    }

    fun favorite(
        mediaType: MediaStateType, mediaId: Int, favorite: Boolean,
        onFavoriteSuccess: () -> Unit
    ) {
        load(onSuccess = onFavoriteSuccess) {
            useCaseFavorite.invoke(
                MediaActionUseCaseFavoriteParams(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    userId = userSession.userId!!,
                    sessionId = userSession.sessionId!!,
                    favorite = favorite
                )
            )
        }
    }

    fun watchlist(
        mediaType: MediaStateType,
        mediaId: Int,
        watchlist: Boolean,
        onWatchlistSuccess: () -> Unit
    ) {
        load(onSuccess = onWatchlistSuccess) {
            useCaseWatchlist.invoke(
                MediaActionUseCaseWatchlistParam(
                    mediaType = mediaType,
                    mediaId = mediaId,
                    userId = userSession.userId!!,
                    sessionId = userSession.sessionId!!,
                    watchlist = watchlist
                )
            )
        }
    }


    private fun load(onSuccess: () -> Unit, invoker: suspend () -> MediaActionModel) {
        if (_state.value !is MediaActionState.Loading)
            _state.value = MediaActionState.Loading
        viewModelScope.launch {
            val result = safeApiCall {
                invoker()
            }
            when (result) {
                is ResultWrapper.Error -> {
                    _state.value = MediaActionState.Error(result.errorEntity.message)
                    showAlert(result.errorEntity.message)
                    Log.e("MediaActionViewModel", "Error : ${result.errorEntity.message}")
                }

                is ResultWrapper.Success<MediaActionModel> -> {
                    when (result.data.statusCode) {
                        1, 12, 13 -> {
                            _state.value = MediaActionState.Loaded
                            onSuccess()
                        }

                        else -> _state.value = MediaActionState.Error(
                            errorMessage = result.data.statusMessage
                                ?: "An unknown error occurred! Please try again."
                        )
                    }
                }
            }
        }
    }


}