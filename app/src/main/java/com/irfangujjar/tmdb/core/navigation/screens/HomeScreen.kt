package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
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
        val argId: String, val category: MoviesCategories,
        val movieId: Int?
    ) : HomeScreen

    @Serializable
    data class SeeAllTvShows(
        val argId: String, val category: TvShowsCategories,
        val tvId: Int?
    ) : HomeScreen
}