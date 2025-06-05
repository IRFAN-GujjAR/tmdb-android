package com.irfangujjar.tmdb.core.navigation.graphs

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.irfangujjar.tmdb.core.navigation.screens.AppScreen
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen

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
        composable(
            AppScreen.Login.route,
            arguments = listOf(
                navArgument(AppScreen.Login.PARAMETER_NAME) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) { backStackEntry ->
            val showBackStack =
                backStackEntry.arguments?.getBoolean(AppScreen.Login.PARAMETER_NAME) == true
            LoginScreen(
                isAppStartedFirstTime = isAppStartedFirstTime,
                showBackStack = showBackStack,
                onBackStackPressed = {
                    navController.popBackStack()
                },
                navigateToMainScreen = {
                    if (showBackStack)
//                        navController.popBackStack(AppScreen.Main.route, inclusive = false)
                        navController.popBackStack()
                    else
                        navController.navigate(AppScreen.Main.route) {
                            popUpTo(0) { inclusive = true } // Clears the entire back stack
                            //popUpTo(Screen.Login.route) { inclusive = true }
                        }
                }
            )
        }
        composable(AppScreen.Main.route) {
            HomeScreen(
                userTheme = userTheme,
//                navigateToLogin = {
//                    navController.navigate(AppScreen.Login.createRoute(true))
//                }
            )
        }
    }
}