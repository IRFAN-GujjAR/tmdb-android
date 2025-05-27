package com.irfangujjar.tmdb.features.app_startup.domain.entities

import com.irfangujjar.tmdb.core.ui.theme.UserTheme

data class AppStartupEntity(
    val isAppStartedFirstTime: Boolean,
    val userTheme: UserTheme,
    val userId: Int? = null,
    val sessionId: String? = null,
)