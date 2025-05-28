package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.navigation.routes.AppRoutes

sealed class AppScreen(val route: String) {
    object Login : AppScreen(AppRoutes.LOGIN)
    object Main : AppScreen(AppRoutes.MAIN)
}