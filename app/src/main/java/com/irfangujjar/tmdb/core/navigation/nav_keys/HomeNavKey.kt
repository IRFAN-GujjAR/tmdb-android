package com.irfangujjar.tmdb.core.navigation.nav_keys

import androidx.navigation3.runtime.NavKey
import com.irfangujjar.tmdb.core.navigation.args_holder.HasArgId
import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import kotlinx.serialization.Serializable


sealed interface HomeNavKey : NavKey {
    @Serializable
    data class MovieDetailsNavKey(
        val movieId: Int,
        val title: String,
        val posterPath: String?,
        val backdropPath: String?
    ) : HomeNavKey

    @Serializable
    data class TvShowDetailsNavKey(
        val tvId: Int,
        val name: String,
        val posterPath: String?,
        val backdropPath: String?
    ) : HomeNavKey

    @Serializable
    data class CelebDetailsNavKey(
        val celebId: Int,
        val name: String
    ) : HomeNavKey


    @Serializable
    data class MovieCreditsNavKey(
        override val argId: String
    ) : HomeNavKey, HasArgId

    @Serializable
    data class TvShowCreditsNavKey(
        override val argId: String
    ) : HomeNavKey, HasArgId

    @Serializable
    data object ThemeNavKey : HomeNavKey

    @Serializable
    data object LoginNavKey : HomeNavKey

    @Serializable
    data object AboutNavKey : HomeNavKey

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

    @Serializable
    data class CastCrewNavKey(override val argId: String) : HomeNavKey, HasArgId

    @Serializable
    data class CollectionDetailsNavKey(
        val collectionId: Int,
        val name: String,
        val posterPath: String?,
        val backdropPath: String?
    ) : HomeNavKey

    @Serializable
    data class SeasonDetailsNavKey(
        val tvId: Int,
        val tvShowName: String,
        val seasonName: String,
        val seasonNo: Int,
        val tvShowPosterPath: String?,
        val episodeImagePlaceHolder: String?
    ) : HomeNavKey

    @Serializable
    data class SeeAllSeasonsNavKey(
        override val argId: String,
        val tvId: Int,
        val tvShowName: String,
        val tvShowPosterPath: String?,
        val episodeImagePlaceHolder: String?
    ) : HomeNavKey, HasArgId

    @Serializable
    data class TMDBMediaListNavKey(
        val category: TMDBMediaListCategory,
    ) : HomeNavKey

    @Serializable
    data object NotSignedInDialogBoxNavKey : HomeNavKey
}
