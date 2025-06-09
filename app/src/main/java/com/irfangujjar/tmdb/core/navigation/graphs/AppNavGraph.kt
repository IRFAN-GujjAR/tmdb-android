package com.irfangujjar.tmdb.core.navigation.graphs

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.irfangujjar.tmdb.core.navigation.screens.AppScreen
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: AppScreen,
    isAppStartedFirstTime: Boolean,
    userTheme: UserTheme
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<AppScreen.Login>(
//            AppScreen.Login,
//            arguments = listOf(
//                navArgument(AppScreen.Login.PARAMETER_NAME) {
//                    type = NavType.BoolType
//                    defaultValue = false
//                }
//            )
        ) { backStackEntry ->
            LoginScreen(
                isAppStartedFirstTime = isAppStartedFirstTime,
                showBackStack = false,
                onBackStackPressed = {
                    navController.popBackStack()
                },
                navigateToMainScreen = {
//                    if (showBackStack)
////                        navController.popBackStack(AppScreen.Main.route, inclusive = false)
//                        navController.popBackStack()
//                    else
                    navController.navigate(AppScreen.Main) {
                        popUpTo(0) { inclusive = true } // Clears the entire back stack
                        //popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        composable<AppScreen.Main> {
            HomeScreen(
                userTheme = userTheme,
//                navigateToLogin = {
//                    navController.navigate(AppScreen.Login.createRoute(true))
//                }
            )
        }
    }
}