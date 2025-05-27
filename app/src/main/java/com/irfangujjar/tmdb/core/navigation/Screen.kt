package com.irfangujjar.tmdb.core.navigation

sealed class Screen(val route: String) {
    object Login : Screen(Routes.LOGIN)
    object Main : Screen(Routes.MAIN)
}