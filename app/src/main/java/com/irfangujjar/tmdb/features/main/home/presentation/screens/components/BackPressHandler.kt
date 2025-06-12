package com.irfangujjar.tmdb.features.main.home.presentation.screens.components

import androidx.compose.runtime.MutableState
import androidx.navigation3.runtime.NavBackStack
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey

fun onBackStackPressed(
    currentKey: MutableState<BottomNavKey>,
    moviesBackStack: NavBackStack,
    tvShowsBackStack: NavBackStack,
    celebsBackStack: NavBackStack,
    searchBackStack: NavBackStack,
    tmdbBackStack: NavBackStack
) {
    when (currentKey.value) {
        BottomNavKey.MoviesNavKey -> {
            moviesBackStack.removeLastOrNull()
        }

        BottomNavKey.TVShowsNavKey -> {
            if (tvShowsBackStack.size > 1)
                tvShowsBackStack.removeLastOrNull()
            else {
                currentKey.value = BottomNavKey.MoviesNavKey
            }
        }

        BottomNavKey.CelebsNavKey -> {
            if (celebsBackStack.size > 1)
                celebsBackStack.removeLastOrNull()
            else {
                currentKey.value = BottomNavKey.MoviesNavKey
            }
        }

        BottomNavKey.SearchNavKey -> {
            if (searchBackStack.size > 1)
                searchBackStack.removeLastOrNull()
            else {
                currentKey.value = BottomNavKey.MoviesNavKey
            }
        }

        BottomNavKey.TMDBNavKey -> {
            if (tmdbBackStack.size > 1)
                tvShowsBackStack.removeLastOrNull()
            else {
                currentKey.value = BottomNavKey.MoviesNavKey
            }
        }
    }
}