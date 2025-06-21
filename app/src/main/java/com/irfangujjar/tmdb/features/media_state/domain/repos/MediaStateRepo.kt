package com.irfangujjar.tmdb.features.media_state.domain.repos

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel

interface MediaStateRepo {
    suspend fun load(
        type: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaStateModel
}