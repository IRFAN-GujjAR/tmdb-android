package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.navigation.routes.AppRoutes

sealed class AppScreen(val route: String) {
    object Login : AppScreen(AppRoutes.LOGIN) {
        const val PARAMETER_NAME = "showBackStack"
        fun createRoute(showBackStack: Boolean): String = "login?$PARAMETER_NAME=$showBackStack"
    }

    object Main : AppScreen(AppRoutes.MAIN)
}