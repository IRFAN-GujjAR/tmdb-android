package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens.SeeAllCelebsScreen
import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.onBackStackPressed
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens.SeeAllMoviesScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens.SeeAllTvShowsScreen

@Composable
fun EntryProviderBuilder<NavKey>.HomeScreenEntries(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackBarHostState: SnackbarHostState,
    moviesBackStack: NavBackStack,
    tvShowsBackStack: NavBackStack,
    celebsBackStack: NavBackStack,
    currentKey: MutableState<BottomNavKey>,
    searchBackStack: NavBackStack,
    tmdbBackStack: NavBackStack
) {
    BottomNavEntries(
        outerPadding = outerPadding,
        userTheme = userTheme,
        snackBarHostState = snackBarHostState,
        moviesBackStack = moviesBackStack,
        tvShowsBackStack = tvShowsBackStack,
        celebsBackStack = celebsBackStack,
        searchBackStack = searchBackStack,
        tmdbBackStack = tmdbBackStack
    )
    entry<HomeNavKey.SeeAllMoviesNavKey> {
        SeeAllMoviesScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = {
                onBackStackPressed(
                    currentKey = currentKey,
                    moviesBackStack = moviesBackStack,
                    tvShowsBackStack = tvShowsBackStack,
                    celebsBackStack = celebsBackStack,
                    searchBackStack = searchBackStack,
                    tmdbBackStack = tmdbBackStack,
                )
            },
        )
    }
    entry<HomeNavKey.SeeAllTvShowsNavKey> {
        SeeAllTvShowsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = {
                onBackStackPressed(
                    currentKey = currentKey,
                    moviesBackStack = moviesBackStack,
                    tvShowsBackStack = tvShowsBackStack,
                    celebsBackStack = celebsBackStack,
                    searchBackStack = searchBackStack,
                    tmdbBackStack = tmdbBackStack,
                )
            }
        )
    }

    entry<HomeNavKey.SeeAllCelebsNavKey> {
        SeeAllCelebsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = {
                onBackStackPressed(
                    currentKey = currentKey,
                    moviesBackStack = moviesBackStack,
                    tvShowsBackStack = tvShowsBackStack,
                    celebsBackStack = celebsBackStack,
                    searchBackStack = searchBackStack,
                    tmdbBackStack = tmdbBackStack,
                )
            }
        )
    }

    entry<HomeNavKey.LoginNavKey> {
        LoginScreen(
            showBackStack = true,
            onBackStackPressed = {
                tmdbBackStack.removeLastOrNull()
            },
            navigateToMainScreen = {
                tmdbBackStack.removeLastOrNull()
            }
        )
    }
}