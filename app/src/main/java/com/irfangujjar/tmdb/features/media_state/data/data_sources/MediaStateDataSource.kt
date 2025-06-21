package com.irfangujjar.tmdb.features.media_state.data.data_sources

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_state.data.data_sources.api.MediaStateApi
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel

interface MediaStateDataSource {
    suspend fun load(
        type: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaStateModel
}

class MediaStateDataSourceImpl(
    private val api: MediaStateApi
) : MediaStateDataSource {
    override suspend fun load(
        type: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaStateModel = api.load(type = type.value, mediaId = mediaId, sessionId = sessionId)
}