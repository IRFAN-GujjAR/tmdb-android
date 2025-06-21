package com.irfangujjar.tmdb.features.media_state.data.repos

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_state.data.data_sources.MediaStateDataSource
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel
import com.irfangujjar.tmdb.features.media_state.domain.repos.MediaStateRepo

class MediaStateRepoImpl(
    private val dataSource: MediaStateDataSource
) : MediaStateRepo {
    override suspend fun load(
        type: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaStateModel = dataSource.load(
        type = type,
        mediaId = mediaId,
        sessionId = sessionId
    )
}