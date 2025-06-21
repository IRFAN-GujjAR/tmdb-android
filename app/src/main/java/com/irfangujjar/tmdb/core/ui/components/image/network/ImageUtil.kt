package com.irfangujjar.tmdb.core.ui.components.image.network

import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.core.ui.util.StillSizes


fun dummyPreviewImage(
    mediaImageType: MediaImageType,
    isLandscape: Boolean = false,
    backdropSizes: BackdropSizes,
    posterSize: PosterSizes,
    profileSize: ProfileSizes,
    stillSize: StillSizes
): Int = when (mediaImageType) {
    MediaImageType.Movie -> {
        if (isLandscape) {
            when (backdropSizes) {
                BackdropSizes.w300 -> R.drawable.movie_backdrop_w300
                BackdropSizes.w780 -> R.drawable.movie_backdrop_w780
                BackdropSizes.w1280 -> R.drawable.movie_backdrop_w1280
                BackdropSizes.original -> R.drawable.movie_backdrop_original
            }
        } else {
            when (posterSize) {
                PosterSizes.w92 -> R.drawable.movie_poster_w92
                PosterSizes.w154 -> R.drawable.movie_poster_w154
                PosterSizes.w185 -> R.drawable.movie_poster_w185
                PosterSizes.w342 -> R.drawable.movie_poster_w342
                PosterSizes.w500 -> R.drawable.movie_poster_w500
                PosterSizes.w780 -> R.drawable.movie_poster_w780
                PosterSizes.original -> R.drawable.movie_poster_original
            }
        }

    }

    MediaImageType.TvShow -> {
        if (isLandscape) {
            when (backdropSizes) {
                BackdropSizes.w300 -> R.drawable.tv_backdrop_w300
                BackdropSizes.w780 -> R.drawable.tv_backdrop_w780
                BackdropSizes.w1280 -> R.drawable.tv_backdrop_w1280
                BackdropSizes.original -> R.drawable.tv_backdrop_original
            }
        } else {
            when (posterSize) {
                PosterSizes.w92 -> R.drawable.tv_poster_w92
                PosterSizes.w154 -> R.drawable.tv_poster_w154
                PosterSizes.w185 -> R.drawable.tv_poster_w185
                PosterSizes.w342 -> R.drawable.tv_poster_w342
                PosterSizes.w500 -> R.drawable.tv_poster_w500
                PosterSizes.w780 -> R.drawable.tv_poster_w780
                PosterSizes.original -> R.drawable.tv_poster_original
            }
        }
    }

    MediaImageType.Celebrity -> {
        when (profileSize) {
            ProfileSizes.w45 -> R.drawable.celebrity_profile_w45
            ProfileSizes.w92 -> R.drawable.celebrity_profile_w92
            ProfileSizes.w185 -> R.drawable.celebrity_profile_w185
            ProfileSizes.h632 -> R.drawable.celebrity_profile_h632
            ProfileSizes.original -> R.drawable.celebrity_profile_original
        }
    }

    MediaImageType.Episode -> {
        when (stillSize) {
            StillSizes.w185 -> R.drawable.tv_backdrop_w300
        }
    }
}
