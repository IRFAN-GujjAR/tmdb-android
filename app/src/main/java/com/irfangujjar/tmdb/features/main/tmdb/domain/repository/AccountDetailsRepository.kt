package com.irfangujjar.tmdb.features.main.tmdb.domain.repository

import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import kotlinx.coroutines.flow.Flow

interface AccountDetailsRepository {
    suspend fun getAccountDetails(sessionId: String): AccountDetailsEntity
    suspend fun watchAccountDetails(): Flow<AccountDetailsEntity?>
}