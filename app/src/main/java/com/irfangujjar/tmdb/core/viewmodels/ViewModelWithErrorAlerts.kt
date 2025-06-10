package com.irfangujjar.tmdb.core.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

open class ViewModelWithErrorAlerts : ViewModel() {
    var alertMessage = ""
        private set
    var showAlert by mutableStateOf(false)
        private set

    fun clearAlert() {
        showAlert = false
        alertMessage = ""
    }

    protected fun showAlert(message: String) {
        alertMessage = message
        showAlert = true
    }
}