package com.irfangujjar.tmdb.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    var username by mutableStateOf("")
        private set
    var userNameError by mutableStateOf(false)
        private set
    var password by mutableStateOf("")
        private set
    var passwordError by mutableStateOf(false)
        private set
    var showPassword by mutableStateOf(false)
        private set

    fun updateUsername(input: String) {
        username = input
        if (userNameError)
            userNameError = false
    }

    fun updatePassword(input: String) {
        password = input
        if (passwordError)
            passwordError = false
    }

    fun toggleShowPassword() {
        showPassword = !showPassword
    }

    fun signIn() {
        if (username.isEmpty()) {
            userNameError = true
        }
        if (password.isEmpty()) {
            passwordError = true
        }
        if (!userNameError && !passwordError) {
            Log.d("LoginViewModel", "Signing in with username: $username and password: $password")
        }
    }
}