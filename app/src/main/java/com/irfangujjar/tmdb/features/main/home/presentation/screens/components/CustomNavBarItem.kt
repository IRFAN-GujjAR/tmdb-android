package com.irfangujjar.tmdb.features.main.home.presentation.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector

sealed class CustomNavBarItem(
    val index: Int,
    val label: String,
    val icon: ImageVector
) {

    object Movies : CustomNavBarItem(
        index = 0,
        label = "Movies",
        icon = Icons.Default.Movie
    )

    object TVShows : CustomNavBarItem(
        index = 1,
        label = "Tv Shows",
        icon = Icons.Default.Tv
    )

    object Celebs : CustomNavBarItem(
        index = 2,
        label = "Celebrities",
        icon = Icons.Default.Person
    )

    object Search : CustomNavBarItem(
        index = 3,
        label = "Search",
        icon = Icons.Default.Search
    )

    object TMDB : CustomNavBarItem(
        index = 4,
        label = "TMDb",
        icon = Icons.Default.AccountBox
    )
    
    companion object {
        val items = listOf(Movies, TVShows, Celebs, Search, TMDB)
    }
}
//sealed class BottomNavBarScreen(
//    val route: String,
//    val index: Int,
//    val label: String,
//    val icon: ImageVector
//) {
//    object Movies : BottomNavBarScreen(
//        route = BottomNavBarRoutes.MOVIES,
//        index = 0,
//        label = "Movies",
//        icon = Icons.Default.Movie
//    )
//
//    object TVShows : BottomNavBarScreen(
//        route = BottomNavBarRoutes.TV_SHOWS,
//        index = 1,
//        label = "Tv Shows",
//        icon = Icons.Default.Tv
//    )
//
//    object Celebs : BottomNavBarScreen(
//        route = BottomNavBarRoutes.CELEBS,
//        index = 2,
//        label = "Celebrities",
//        icon = Icons.Default.Person
//    )
//
//    object Search : BottomNavBarScreen(
//        route = BottomNavBarRoutes.SEARCH,
//        index = 3,
//        label = "Search",
//        icon = Icons.Default.Search
//    )
//
//    object TMDB : BottomNavBarScreen(
//        route = BottomNavBarRoutes.TMDB,
//        index = 4,
//        label = "TMDb",
//        icon = Icons.Default.AccountBox
//    )
//
//    companion object {
//        val items = listOf(Movies, TVShows, Celebs, Search, TMDB)
//    }
//}