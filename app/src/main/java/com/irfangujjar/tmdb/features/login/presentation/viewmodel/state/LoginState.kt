package com.irfangujjar.tmdb.features.login.presentation.viewmodel.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity

sealed class LoginState {
    object Empty : LoginState()
    object LoggingIn : LoginState()
    data class LoggedIn(val userSession: SessionEntity) : LoginState()
    data class Error(val error: ErrorEntity) : LoginState()
}
