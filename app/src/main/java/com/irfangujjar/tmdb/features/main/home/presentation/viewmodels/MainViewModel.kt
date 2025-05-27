package com.irfangujjar.tmdb.features.main.home.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.irfangujjar.tmdb.UserSession
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {

}