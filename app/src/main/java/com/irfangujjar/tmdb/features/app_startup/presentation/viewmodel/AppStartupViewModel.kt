package com.irfangujjar.tmdb.features.app_startup.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.app_startup.domain.usecase.AppStartupUseCaseLoadData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppStartupViewModel @Inject constructor(
    private val userSession: UserSession,
    private val useCaseLoadData: AppStartupUseCaseLoadData
) : ViewModel() {
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading
    private val _userTheme = mutableStateOf(UserTheme.SYSTEM)
    val userTheme: State<UserTheme> = _userTheme
    private val _isAppStartedFirstTime = mutableStateOf(false)
    val isAppStartedFirstTime: State<Boolean> = _isAppStartedFirstTime


    init {
        viewModelScope.launch {
            val appData = useCaseLoadData.invoke()
            _userTheme.value = appData.userTheme
            _isAppStartedFirstTime.value = appData.isAppStartedFirstTime
            if (userSession.sessionId != null && userSession.userId != null) {
                userSession.updateSession(
                    sessionId = appData.sessionId!!,
                    userId = appData.userId!!
                )
            }
            _isLoading.value = false
        }
    }
}