package com.irfangujjar.tmdb.features.media_state.domain.models

data class MediaStateModel(
    val id: Int,
    val favorite: Boolean,
    val rated: RatedModel,
    val watchlist: Boolean
)

