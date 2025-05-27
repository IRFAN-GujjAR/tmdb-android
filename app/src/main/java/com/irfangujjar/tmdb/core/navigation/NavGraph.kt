package com.irfangujjar.tmdb.core.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.irfangujjar.tmdb.features.login.presentation.ui.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.home.presentation.screens.MainScreen

@Composable
fun TMDbNavGraph(
    navController: NavHostController,
    startDestination: String,
    isAppStartedFirstTime: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                isAppStartedFirstTime = isAppStartedFirstTime,
                navigateToMainScreen = {
                    Log.d("NavGraph", "Navigate")
                    navController.navigate(Screen.Main.route) {
                        popUpTo(0) { inclusive = true } // Clears the entire back stack
//                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}