package com.irfangujjar.tmdb.core.ui.util

import com.irfangujjar.tmdb.core.urls.URLS

enum class MediaImageType {
    Movie,
    TvShow,
    Celebrity,
    Episode
}

fun MediaImageType.isMovie() = this == MediaImageType.Movie
fun MediaImageType.isTvShow() = this == MediaImageType.TvShow
fun MediaImageType.isCelebrity() = this == MediaImageType.Celebrity

enum class PosterSizes {
    w92, w154, w185, w342, w500, w780, original
}

enum class BackdropSizes {
    w300, w780, w1280, original
}

enum class ProfileSizes {
    w45, w92, w185, h632, original
}

enum class StillSizes { w185 }

fun PosterSizes.getUrl(posterPath: String) = URLS.IMAGE_BASE_URL + this.name + posterPath

fun BackdropSizes.getUrl(backdropPath: String) = URLS.IMAGE_BASE_URL + this.name + backdropPath

fun ProfileSizes.getUrl(profilePath: String) = URLS.IMAGE_BASE_URL + this.name + profilePath

fun StillSizes.getUrl(stillPath: String) = URLS.IMAGE_BASE_URL + this.name + stillPath












