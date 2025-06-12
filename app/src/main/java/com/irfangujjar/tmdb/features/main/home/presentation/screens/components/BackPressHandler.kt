package com.irfangujjar.tmdb.features.main.home.presentation.screens.components

import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey

fun onBackStackPressed(
    currentKey: BottomNavKey,
    tvShowsBackStackSize: Int,
    celebsBackStackSize: Int,
    searchBackStackSize: Int,
    tmdbBackStackSize: Int,
    onPopMoviesBackStack: () -> Unit,
    onPopTvShowsBackStack: () -> Unit,
    onPopCelebsBackStack: () -> Unit,
    onPopSearchBackStack: () -> Unit,
    onPopTMDbBackStack: () -> Unit,
    onSetMoviesScreen: () -> Unit
) {
    when (currentKey) {
        BottomNavKey.MoviesNavKey -> {
            onPopMoviesBackStack()
        }

        BottomNavKey.TVShowsNavKey -> {
            if (tvShowsBackStackSize > 1)
                onPopTvShowsBackStack()
            else {
                onSetMoviesScreen()
            }
        }

        BottomNavKey.CelebsNavKey -> {
            if (celebsBackStackSize > 1)
                onPopCelebsBackStack()
            else {
                onSetMoviesScreen()
            }
        }

        BottomNavKey.SearchNavKey -> {
            if (searchBackStackSize > 1)
                onPopSearchBackStack()
            else {
                onSetMoviesScreen()
            }
        }

        BottomNavKey.TMDBNavKey -> {
            if (tmdbBackStackSize > 1)
                onPopTMDbBackStack()
            else {
                onSetMoviesScreen()
            }
        }
    }
}