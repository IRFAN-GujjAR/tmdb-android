package com.irfangujjar.tmdb.features.media_action.domain.usecases.params

import com.irfangujjar.tmdb.core.api.MediaStateType

data class MediaActionUseCaseWatchlistParam(
    val mediaType: MediaStateType,
    val mediaId: Int,
    val userId: Int,
    val sessionId: String,
    val watchlist: Boolean
)