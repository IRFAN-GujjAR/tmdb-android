package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.CustomNavBarItem
import kotlinx.serialization.Serializable

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

sealed interface BottomNavBarScreen {

    val route: String
        get() = this::class.qualifiedName ?: ""

    @Serializable
    object Movies : BottomNavBarScreen

    @Serializable
    object TVShows : BottomNavBarScreen

    @Serializable
    object Celebs : BottomNavBarScreen

    @Serializable
    object Search : BottomNavBarScreen

    @Serializable
    object TMDB : BottomNavBarScreen

    companion object {
        val items = listOf(Movies, TVShows, Celebs, Search, TMDB)
    }

    fun getCustomNavBarItem(): CustomNavBarItem =
        when (this) {
            Celebs -> CustomNavBarItem.Celebs
            Movies -> CustomNavBarItem.Movies
            Search -> CustomNavBarItem.Search
            TMDB -> CustomNavBarItem.TMDB
            TVShows -> CustomNavBarItem.TVShows
        }

}
