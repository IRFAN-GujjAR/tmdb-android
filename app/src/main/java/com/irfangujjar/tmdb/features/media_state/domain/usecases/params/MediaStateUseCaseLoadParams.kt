package com.irfangujjar.tmdb.features.media_state.domain.usecases.params

import com.irfangujjar.tmdb.core.api.MediaStateType

data class MediaStateUseCaseLoadParams(
    val type: MediaStateType,
    val mediaId: Int,
    val sessionId: String
)