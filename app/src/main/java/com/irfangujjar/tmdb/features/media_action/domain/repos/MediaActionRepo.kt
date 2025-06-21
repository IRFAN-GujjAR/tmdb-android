package com.irfangujjar.tmdb.features.media_action.domain.repos

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel

interface MediaActionRepo {
    suspend fun favorite(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        favorite: Boolean
    ): MediaActionModel

    suspend fun rate(
        mediaType: MediaStateType,
        mediaId: Int,
        rating: Int,
        sessionId: String
    ): MediaActionModel

    suspend fun unRate(
        mediaType: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaActionModel

    suspend fun watchlist(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        watchlist: Boolean
    ): MediaActionModel
}