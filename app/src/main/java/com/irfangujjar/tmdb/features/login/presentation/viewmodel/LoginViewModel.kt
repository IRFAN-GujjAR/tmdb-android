package com.irfangujjar.tmdb.features.login.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.urls.URLS
import com.irfangujjar.tmdb.features.login.domain.entities.SessionEntity
import com.irfangujjar.tmdb.features.login.domain.usecase.LoginUseCase
import com.irfangujjar.tmdb.features.login.domain.usecase.params.LoginParams
import com.irfangujjar.tmdb.features.login.presentation.viewmodel.state.LoginState
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.params.AccountDetailsParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val accountDetailsUseCaseLoad: AccountDetailsUseCaseLoad,
    private val userSession: UserSession
) : ViewModel() {


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

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Empty)
    val loginState: StateFlow<LoginState> = _loginState


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
            viewModelScope.launch(Dispatchers.IO) {
                _loginState.value = LoginState.LoggingIn
                val result = safeApiCall<SessionEntity> {
                    loginUseCase.invoke(LoginParams(username, password))
                }
                when (result) {
                    is ResultWrapper.Error -> {
                        _loginState.value = LoginState.Error(error = result.errorEntity)
                    }

                    is ResultWrapper.Success<SessionEntity> -> {
                        val accountDetails = getAccountDetails(result.data.sessionId)
                        if (accountDetails != null) {
                            _loginState.value = LoginState.LoggedIn(userSession = result.data)
                            userSession.updateSessionId(result.data.sessionId)
                            userSession.updateAccountDetails(accountDetails = accountDetails)
                        }
                    }
                }

            }
        }
    }

    private suspend fun getAccountDetails(sessionId: String): AccountDetailsEntity? {
        val result = safeApiCall {
            accountDetailsUseCaseLoad.invoke(AccountDetailsParams(sessionId))
        }
        when (result) {
            is ResultWrapper.Error -> {
                _loginState.value = LoginState.Error(error = result.errorEntity)
                return null
            }

            is ResultWrapper.Success -> {
                return result.data
            }
        }
    }

    fun resetLoginState() {
        _loginState.value = LoginState.Empty
    }

    fun signUp(context: Context) {
        val intent = Intent(Intent.ACTION_VIEW, URLS.SIGN_UP_URL.toUri())
        context.startActivity(intent)
    }

}




