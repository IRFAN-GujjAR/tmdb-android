package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import kotlinx.serialization.Serializable

sealed interface HomeScreen {
    val route: String
        get() = this::class.qualifiedName ?: ""

    @Serializable
    object MovieDetails : HomeScreen

    @Serializable
    object TvShowDetails : HomeScreen

    @Serializable
    object CelebDetails : HomeScreen

    @Serializable
    object SearchDetails : HomeScreen

    @Serializable
    object TMDBAppearances : HomeScreen

    @Serializable
    object Login : HomeScreen

    @Serializable
    data class SeeAllMovies(
        val argsId: String, val category: MoviesCategories,
        val movieId: Int? = null
    ) : HomeScreen
}