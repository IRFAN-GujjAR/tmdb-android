package com.irfangujjar.tmdb.features.main.tmdb.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseLoadWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseWatch
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.params.AccountDetailsParams
import com.irfangujjar.tmdb.features.main.tmdb.presentation.viewmodels.state.AccountDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TMDBViewModel @Inject constructor(
    private val userSession: UserSession,
    private val accountDetailsUseCaseWatch: AccountDetailsUseCaseWatch,
    private val accountDetailsUseCaseLoadWithoutId: AccountDetailsUseCaseLoadWithoutId
) : ViewModelWithErrorAlerts() {

    private val _state: MutableStateFlow<AccountDetailsState> =
        MutableStateFlow(AccountDetailsState.Loading)
    val state: StateFlow<AccountDetailsState> = _state

    var isRefreshing by mutableStateOf(false)
        private set

    private val firstEmissionDeferred = CompletableDeferred<Unit>()

    private val _showNotSignedInMessage = mutableStateOf(false)
    val showNotSignedInMessage: State<Boolean> = _showNotSignedInMessage

    fun showNotSignedInMessage() {
        _showNotSignedInMessage.value = true
    }

    fun hideNotSignedInMessage() {
        _showNotSignedInMessage.value = false
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            watchAccountDetails()
        }
        if (userSession.isLoggedIn())
            viewModelScope.launch(Dispatchers.IO) {
                loadAccountDetails(userSession.sessionId!!)
            }
    }

    private suspend fun watchAccountDetails() {
        accountDetailsUseCaseWatch.invoke().collect { accountDetails ->
            if (accountDetails != null) {
                _state.value = AccountDetailsState.Loaded(accountDetails = accountDetails)
            } else {
                _state.value = AccountDetailsState.Empty
            }
            if (!firstEmissionDeferred.isCompleted)
                firstEmissionDeferred.complete(Unit)
        }
    }

    private suspend fun loadAccountDetails(sessionId: String) {
        val result = safeApiCall {
            if (state.value is AccountDetailsState.Error) {
                _state.value = AccountDetailsState.Loading
            }
            accountDetailsUseCaseLoadWithoutId.invoke(
                params = AccountDetailsParams(sessionId = sessionId)
            )
        }
        firstEmissionDeferred.await()
        if (result is ResultWrapper.Error) {
            if (state.value is AccountDetailsState.Loaded) {
                showAlert(result.errorEntity.message)
                _state.value = AccountDetailsState.ErrorWithCache(
                    accountDetails = (state.value as AccountDetailsState.Loaded).accountDetails,
                    error = result.errorEntity
                )
            } else {
                _state.value = AccountDetailsState.Error(error = result.errorEntity)
            }
        }
    }

    fun retry() {
        if (userSession.isLoggedIn())
            viewModelScope.launch(Dispatchers.IO) {
                loadAccountDetails(userSession.sessionId!!)
            }
    }

    fun refresh() {
        if (userSession.isLoggedIn())
            viewModelScope.launch(Dispatchers.IO) {
                isRefreshing = true
                loadAccountDetails(userSession.sessionId!!)
                isRefreshing = false
            }
    }
}