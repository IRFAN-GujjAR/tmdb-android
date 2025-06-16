package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.screens.CelebDetailsScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens.SeeAllCelebsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.MovieDetailsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens.SeeAllMoviesScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.TvShowDetailsScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens.SeeAllTvShowsScreen

@Composable
fun EntryProviderBuilder<NavKey>.HomeScreenEntries(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackBarHostState: SnackbarHostState,
    onNavigateToSeeAllMovies: (HomeNavKey.SeeAllMoviesNavKey) -> Unit,
    onNavigateToSeeAllTvShows: (HomeNavKey.SeeAllTvShowsNavKey) -> Unit,
    onNavigateToSeeAllCelebs: (HomeNavKey.SeeAllCelebsNavKey) -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackPressed: () -> Unit
) {
    BottomNavEntries(
        outerPadding = outerPadding,
        userTheme = userTheme,
        snackBarHostState = snackBarHostState,
        onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
        onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
        onNavigateToSeeCelebs = onNavigateToSeeAllCelebs,
        onNavigateToLogin = onNavigateToLogin,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onNavigateToTvShowDetails = onNavigateToTvShowDetails,
        onNavigateToCelebDetails = onNavigateToCelebDetails
    )
    entry<HomeNavKey.SeeAllMoviesNavKey> {
        SeeAllMoviesScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }
    entry<HomeNavKey.SeeAllTvShowsNavKey> {
        SeeAllTvShowsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }

    entry<HomeNavKey.SeeAllCelebsNavKey> {
        SeeAllCelebsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToCelebDetails = onNavigateToCelebDetails
        )
    }

    entry<HomeNavKey.MovieDetailsNavKey> {
        MovieDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }

    entry<HomeNavKey.TvShowDetailsNavKey> {
        TvShowDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }

    entry<HomeNavKey.CelebDetailsNavKey> {
        CelebDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }

    entry<HomeNavKey.LoginNavKey> {
        LoginScreen(
            showBackStack = true,
            onBackStackPressed = {
                onBackPressed
            },
            navigateToMainScreen = {
                onBackPressed
            }
        )
    }
}