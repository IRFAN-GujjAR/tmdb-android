package com.irfangujjar.tmdb.core.navigation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.irfangujjar.tmdb.core.navigation.routes.HomeRoutes

sealed class BottomNavBarScreen(
    val route: String,
    val index: Int,
    val label: String,
    val icon: ImageVector
) {
    object Movies : BottomNavBarScreen(
        route = HomeRoutes.BottomNavBarRoutes.MOVIES,
        index = 0,
        label = "Movies",
        icon = Icons.Default.Movie
    )

    object TVShows : BottomNavBarScreen(
        route = HomeRoutes.BottomNavBarRoutes.TV_SHOWS,
        index = 1,
        label = "Tv Shows",
        icon = Icons.Default.Tv
    )

    object Celebs : BottomNavBarScreen(
        route = HomeRoutes.BottomNavBarRoutes.CELEBS,
        index = 2,
        label = "Celebrities",
        icon = Icons.Default.Person
    )

    object Search : BottomNavBarScreen(
        route = HomeRoutes.BottomNavBarRoutes.SEARCH,
        index = 3,
        label = "Search",
        icon = Icons.Default.Search
    )

    object TMDB : BottomNavBarScreen(
        route = HomeRoutes.BottomNavBarRoutes.TMDB,
        index = 4,
        label = "TMDb",
        icon = Icons.Default.AccountBox
    )

    companion object {
        val items = listOf(Movies, TVShows, Celebs, Search, TMDB)
    }
}