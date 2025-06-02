package com.irfangujjar.tmdb.core.api.query.values

enum class APIQueryFieldValuesMovieTvShowAppendToResponse(name: String) {
    CREDITS("credits"),
    IMAGES("images"),
    VIDEOS("videos"),
    RECOMMENDATIONS("recommendations"),
    SIMILAR("similar"),
    ALL("credits%2Cimages%2Cvideos%2Crecommendations%2Csimilar"),
}
