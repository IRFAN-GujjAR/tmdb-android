package com.irfangujjar.tmdb.core.navigation.routes

import com.irfangujjar.tmdb.core.navigation.screens.AppScreen

object AppRoutes {
    const val LOGIN = "login?${AppScreen.Login.PARAMETER_NAME}={${AppScreen.Login.PARAMETER_NAME}}"
    const val MAIN = "main"
}