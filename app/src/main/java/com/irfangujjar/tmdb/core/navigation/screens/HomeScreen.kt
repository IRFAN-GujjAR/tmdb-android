package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.navigation.routes.HomeRoutes

sealed class HomeScreen(
    val route: String
) {
    object MovieDetails : HomeScreen(HomeRoutes.Movies.DETAILS)
    object TvShowDetails : HomeScreen(HomeRoutes.TvShows.DETAILS)
    object CelebDetails : HomeScreen(HomeRoutes.Celebs.DETAILS)
    object SearchDetails : HomeScreen(HomeRoutes.Search.DETAILS)
    object TMDBAppearances : HomeScreen(HomeRoutes.TMDB.APPEARANCES)
    object Login : HomeScreen(HomeRoutes.TMDB.LOGIN)
}