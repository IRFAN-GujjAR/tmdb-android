package com.irfangujjar.tmdb.core.viewmodels

import androidx.lifecycle.ViewModel
import com.irfangujjar.tmdb.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SignInStatusViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {

    private val _state = MutableStateFlow<SignInStatusState>(SignInStatusState.SignedOut)
    val state: StateFlow<SignInStatusState> = _state

    init {
        if (userSession.isLoggedIn())
            _state.value = SignInStatusState.SignedIn
    }

    fun changeStatusToSingedIn() {
        _state.value = SignInStatusState.SignedIn
    }

    fun changeStatusToSignedOut() {
        _state.value = SignInStatusState.SignedOut
    }
}

sealed interface SignInStatusState {
    data object SignedOut : SignInStatusState
    data object SignedIn : SignInStatusState
}