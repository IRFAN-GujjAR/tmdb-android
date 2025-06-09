package com.irfangujjar.tmdb.core.navigation.screens

import kotlinx.serialization.Serializable

//sealed class AppScreen(val route: String) {
//    object Login : AppScreen(AppRoutes.LOGIN) {
//        const val PARAMETER_NAME = "showBackStack"
//        fun createRoute(showBackStack: Boolean): String = "login?$PARAMETER_NAME=$showBackStack"
//    }
//
//    object Main : AppScreen(AppRoutes.MAIN)
//}

sealed interface AppScreen {
    @Serializable
    object Login : AppScreen

    @Serializable
    object Main : AppScreen
}