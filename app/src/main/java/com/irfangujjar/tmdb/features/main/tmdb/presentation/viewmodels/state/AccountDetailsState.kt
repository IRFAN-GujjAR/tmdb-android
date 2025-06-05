package com.irfangujjar.tmdb.features.main.tmdb.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity

sealed class AccountDetailsState {
    object Loading : AccountDetailsState()
    class Loaded(val accountDetails: AccountDetailsWithoutIdEntity) : AccountDetailsState()
    object Empty : AccountDetailsState()
    class ErrorWithCache(
        val accountDetails: AccountDetailsWithoutIdEntity,
        val error: ErrorEntity
    ) : AccountDetailsState()

    class Error(val error: ErrorEntity) : AccountDetailsState()
}