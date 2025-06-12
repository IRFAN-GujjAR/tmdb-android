package com.irfangujjar.tmdb.core.navigation.nav_keys

import androidx.navigation3.runtime.NavKey
import com.irfangujjar.tmdb.core.navigation.args_holder.HasArgId
import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import kotlinx.serialization.Serializable


sealed interface HomeNavKey : NavKey {
    @Serializable
    data object MovieDetailsNavKey : HomeNavKey

    @Serializable
    data object TvShowDetailsNavKey : HomeNavKey

    @Serializable
    data object CelebDetailsNavKey : HomeNavKey

    @Serializable
    data object SearchDetailsNavKey : HomeNavKey

    @Serializable
    data object TMDBAppearancesNavKey : HomeNavKey

    @Serializable
    data object LoginNavKey : HomeNavKey

    @Serializable
    data class SeeAllMoviesNavKey(
        override val argId: String, val category: MoviesCategory,
        val movieId: Int?
    ) : HomeNavKey, HasArgId

    @Serializable
    data class SeeAllTvShowsNavKey(
        override val argId: String, val category: TvShowsCategory,
        val tvId: Int?
    ) : HomeNavKey, HasArgId

    @Serializable
    data class SeeAllCelebsNavKey(
        override val argId: String, val category: CelebsCategory
    ) : HomeNavKey, HasArgId

}