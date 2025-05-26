package com.irfangujjar.tmdb

import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor() {
    private var _sessionId: String? = null
    val sessionId: String? = _sessionId
    private var _accountDetails: AccountDetailsEntity? = null
    val accountDetails: AccountDetailsEntity? = _accountDetails


    fun updateSessionId(sessionId: String) {
        _sessionId = sessionId
        _accountDetails = null
    }

    fun updateAccountDetails(accountDetails: AccountDetailsEntity) {
        _accountDetails = accountDetails
    }

    fun clearSession() {
        _sessionId = null
        _accountDetails = null
    }
}