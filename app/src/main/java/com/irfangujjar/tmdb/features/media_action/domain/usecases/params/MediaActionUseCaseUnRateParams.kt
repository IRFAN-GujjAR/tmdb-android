package com.irfangujjar.tmdb.features.media_action.domain.usecases.params

import com.irfangujjar.tmdb.core.api.MediaStateType

data class MediaActionUseCaseUnRateParams(
    val mediaType: MediaStateType,
    val mediaId: Int,
    val sessionId: String
)
