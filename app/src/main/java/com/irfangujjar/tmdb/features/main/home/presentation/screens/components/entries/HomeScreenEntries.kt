package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens.SeeAllCelebsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens.SeeAllMoviesScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens.SeeAllTvShowsScreen

@Composable
fun EntryProviderBuilder<NavKey>.HomeScreenEntries(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackBarHostState: SnackbarHostState,
    onNavigateToSeeAllMovies: (key: NavKey, argId: String, movieId: Int?, category: MoviesCategory) -> Unit,
    onNavigateToSeeAllTvShows: (key: NavKey, argId: String, tvId: Int?, category: TvShowsCategory) -> Unit,
    onNavigateToSeeAllCelebs: (key: NavKey, argId: String, category: CelebsCategory) -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackPressed: () -> Unit
) {
    BottomNavEntries(
        outerPadding = outerPadding,
        userTheme = userTheme,
        snackBarHostState = snackBarHostState,
        onNavigateToSeeAllMovies = { key, argId, category ->
            onNavigateToSeeAllMovies(key, argId, null, category)
        },
        onNavigateToSeeAllTvShows = { key, argId, category ->
            onNavigateToSeeAllTvShows(key, argId, null, category)
        },
        onNavigateToSeeCelebs = onNavigateToSeeAllCelebs,
        onNavigateToLogin = onNavigateToLogin
    )
    entry<HomeNavKey.SeeAllMoviesNavKey> {
        SeeAllMoviesScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
        )
    }
    entry<HomeNavKey.SeeAllTvShowsNavKey> {
        SeeAllTvShowsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed
        )
    }

    entry<HomeNavKey.SeeAllCelebsNavKey> {
        SeeAllCelebsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed
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