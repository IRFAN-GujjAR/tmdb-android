package com.irfangujjar.tmdb.core.navigation.graphs

import ThemeScreen
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
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens.SeeAllCelebsScreen
import com.irfangujjar.tmdb.features.main.movies.presentation.screens.MoviesScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens.SeeAllMoviesScreen
import com.irfangujjar.tmdb.features.main.search.presentation.screens.SearchScreen
import com.irfangujjar.tmdb.features.main.tmdb.presentation.screens.TMDBScreen
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.screens.TvShowsScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens.SeeAllTvShowsScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    startDestination: BottomNavBarScreen,
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {

        BottomNavBarScreen.items.forEach { bottomScreen ->
            when (bottomScreen) {
                BottomNavBarScreen.Movies -> composable<BottomNavBarScreen.Movies> {
                    MoviesScreen(
                        outerPadding = outerPadding,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        onNavigateToSeeAllMovies = { argId, category ->
                            navController.navigate(
                                HomeScreen.SeeAllMovies(
                                    argId = argId,
                                    category = category,
                                    movieId = null
                                )
                            )
                        }
                    )
                }

                BottomNavBarScreen.TVShows -> composable<BottomNavBarScreen.TVShows> {
                    TvShowsScreen(
                        outerPadding = outerPadding,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        onNavigateToSeeAllTvShows = { argId, category ->
                            navController.navigate(
                                HomeScreen.SeeAllTvShows(
                                    argId = argId,
                                    category = category,
                                    tvId = null
                                )
                            )
                        }
                    )
                }

                BottomNavBarScreen.Celebs -> composable<BottomNavBarScreen.Celebs> {
                    CelebsScreen(
                        outerPadding = outerPadding,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        onNavigateToSeeAllCelebs = { argId, category ->
                            navController.navigate(
                                HomeScreen.SeeAllCelebs(
                                    argId = argId,
                                    category = category
                                )
                            )
                        }
                    )
                }

                BottomNavBarScreen.Search -> composable<BottomNavBarScreen.Search> {
                    SearchScreen(
                        outerPadding = outerPadding,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        onNavigateToDetailsPage = {
                        }
                    )
                }

                BottomNavBarScreen.TMDB -> composable<BottomNavBarScreen.TMDB> {
                    TMDBScreen(
                        outerPadding = outerPadding,
                        userTheme = userTheme,
                        snackbarHostState = snackbarHostState,
                        navigateToLogin = {
                            navController.navigate(HomeScreen.Login)
                        },
                        navigateToAppearances = {
                            navController.navigate(HomeScreen.TMDBAppearances)
                        }
                    )
                }


            }
        }

        /*   BottomNavBarScreen.items.forEach { bottomScreen ->
               composable(bottomScreen.route) {
                   when (bottomScreen) {
                       BottomNavBarScreen.Movies -> MoviesScreen(
                           outerPadding = outerPadding,
                           userTheme = userTheme,
                           snackbarHostState = snackbarHostState,
                           onNavigateToSeeAllMovies = { argId, category ->
                               navController.navigate(
                                   HomeScreen.SeeAllMovies(
                                       argsId = argId,
                                       category = category
                                   )
                               )
                           }
                       )

                       BottomNavBarScreen.TVShows -> TvShowsScreen(
                           outerPadding = outerPadding,
                           userTheme = userTheme,
                           snackbarHostState = snackbarHostState
                       )

                       BottomNavBarScreen.Celebs -> CelebsScreen(
                           outerPadding = outerPadding,
                           userTheme = userTheme,
                           snackbarHostState = snackbarHostState
                       )

                       BottomNavBarScreen.Search -> SearchScreen(
                           outerPadding = outerPadding,
                           userTheme = userTheme,
                           snackbarHostState = snackbarHostState,
                           onNavigateToDetailsPage = {
                           }
                       )

                       BottomNavBarScreen.TMDB -> TMDBScreen(
                           outerPadding = outerPadding,
                           userTheme = userTheme,
                           snackbarHostState = snackbarHostState,
                           navigateToLogin = {
                               navController.navigate(HomeScreen.Login)
                           },
                           navigateToAppearances = {
                               navController.navigate(HomeScreen.TMDBAppearances)
                           }
                       )
                   }
               }
           }*/


        composable<HomeScreen.SeeAllMovies> {
            SeeAllMoviesScreen(
                outerPadding = outerPadding,
                snackbarHostState = snackbarHostState,
                onBackStackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable<HomeScreen.SeeAllTvShows> {
            SeeAllTvShowsScreen(
                outerPadding = outerPadding,
                snackbarHostState = snackbarHostState,
                onBackStackPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable<HomeScreen.SeeAllCelebs> {
            SeeAllCelebsScreen(
                outerPadding = outerPadding,
                snackbarHostState = snackbarHostState,
                onBackStackPressed = {
                    navController.popBackStack()
                }
            )
        }

        composable<HomeScreen.Login> {
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

        composable<HomeScreen.TMDBAppearances> {
            ThemeScreen(
                userTheme = userTheme,
                outerPadding = outerPadding,
                onBackStackPressed = {
                    navController.popBackStack()
                }
            )
        }


    }
}