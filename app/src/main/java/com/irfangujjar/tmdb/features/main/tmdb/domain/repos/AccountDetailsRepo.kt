package com.irfangujjar.tmdb.features.main.tmdb.domain.repos

import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import kotlinx.coroutines.flow.Flow

interface AccountDetailsRepo {
    suspend fun loadDetailsWithoutId(sessionId: String): AccountDetailsWithoutIdEntity
    suspend fun loadDetails(sessionId: String): AccountDetailsEntity
    suspend fun watchAccountDetails(): Flow<AccountDetailsWithoutIdEntity?>
}