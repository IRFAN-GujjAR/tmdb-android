package com.irfangujjar.tmdb.core.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.celebs.presentation.screens.CelebsScreen
import com.irfangujjar.tmdb.features.main.movies.presentation.screens.MoviesScreen
import com.irfangujjar.tmdb.features.main.search.presentation.screens.SearchScreen
import com.irfangujjar.tmdb.features.main.tmdb.presentation.screens.TMDBScreen
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.screens.TvShowsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues,
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState,
    navigateToLogin: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        BottomNavBarScreen.items.forEach { bottomScreen ->
            composable(bottomScreen.route) {
                when (bottomScreen) {
                    BottomNavBarScreen.Movies -> MoviesScreen(
                        paddingValues = paddingValues,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState
                    )

                    BottomNavBarScreen.TVShows -> TvShowsScreen(
                        paddingValues = paddingValues,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState
                    )

                    BottomNavBarScreen.Celebs -> CelebsScreen(
                        paddingValues = paddingValues,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState
                    )

                    BottomNavBarScreen.Search -> SearchScreen(
                        paddingValues = paddingValues,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState
                    )

                    BottomNavBarScreen.TMDB -> TMDBScreen(
                        paddingValues = paddingValues,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        navigateToLogin = navigateToLogin
                    )
                }
            }
        }

        composable(HomeScreen.Login.route) {
            LoginScreen(
                isAppStartedFirstTime = false,
                showBackStack = true,
                onBackStackPressed = {
                    navController.popBackStack()
                },
                navigateToMainScreen = {
                    navController.popBackStack()
                }
            )
        }
    }
}