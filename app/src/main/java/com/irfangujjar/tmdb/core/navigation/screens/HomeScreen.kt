package com.irfangujjar.tmdb.core.navigation.screens

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
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
        override val argId: String, val category: MoviesCategory,
        val movieId: Int?
    ) : HomeScreen, HasArgId

    @Serializable
    data class SeeAllTvShows(
        override val argId: String, val category: TvShowsCategory,
        val tvId: Int?
    ) : HomeScreen, HasArgId

    @Serializable
    data class SeeAllCelebs(
        override val argId: String, val category: CelebsCategory
    ) : HomeScreen, HasArgId
}