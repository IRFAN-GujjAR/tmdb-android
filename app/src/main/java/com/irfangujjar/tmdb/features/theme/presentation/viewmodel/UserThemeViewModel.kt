package com.irfangujjar.tmdb.features.theme.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.theme.domain.usecase.UserThemeUseCaseSave
import com.irfangujjar.tmdb.features.theme.domain.usecase.UserThemeUseCaseWatch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserThemeViewModel @Inject constructor(
    private val saveUseCase: UserThemeUseCaseSave,
    private val watchUseCase: UserThemeUseCaseWatch
) : ViewModel() {

    private val _userTheme = MutableStateFlow<UserTheme?>(null)
    val userTheme: StateFlow<UserTheme?> = _userTheme

    init {
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            watchUseCase.invoke().collect {
                _userTheme.value = it
            }
        }
    }

    fun saveUserTheme(userTheme: UserTheme) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUseCase.invoke(userTheme)
        }
    }
}