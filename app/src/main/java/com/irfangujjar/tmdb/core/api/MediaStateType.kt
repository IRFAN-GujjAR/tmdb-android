package com.irfangujjar.tmdb.core.api

import com.irfangujjar.tmdb.core.ui.util.MediaImageType

enum class MediaStateType(val value: String) {
    Movie("movie"),
    Tv("tv")
}

fun MediaStateType.mediaImageType(): MediaImageType = when (this) {
    MediaStateType.Movie -> MediaImageType.Movie
    MediaStateType.Tv -> MediaImageType.TvShow
}