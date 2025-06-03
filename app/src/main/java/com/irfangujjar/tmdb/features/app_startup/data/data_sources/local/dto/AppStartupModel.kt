package com.irfangujjar.tmdb.features.app_startup.data.data_sources.local.dto

data class AppStartupModel(
    val isAppStartedFirstTime: Boolean,
    val userTheme: Int?,
    val userId: Int? = null,
    val sessionId: String? = null,
)