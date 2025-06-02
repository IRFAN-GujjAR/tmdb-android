package com.irfangujjar.tmdb.core.navigation.graphs

import HomeScreen
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.irfangujjar.tmdb.core.navigation.screens.AppScreen
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.ui.screens.LoginScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String,
    isAppStartedFirstTime: Boolean,
    userTheme: UserTheme
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(AppScreen.Login.route) {
            LoginScreen(
                isAppStartedFirstTime = isAppStartedFirstTime,
                navigateToMainScreen = {
                    Log.d("NavGraph", "Navigate")
                    navController.navigate(AppScreen.Main.route) {
                        popUpTo(0) { inclusive = true } // Clears the entire back stack
//                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable(AppScreen.Main.route) {
            HomeScreen(
                userTheme = userTheme
            )
        }
    }
}