package com.irfangujjar.tmdb.features.login.presentation.viewmodel.state

sealed class LoginContinueState {
    object Idle : LoginContinueState()
    object Loading : LoginContinueState()
}