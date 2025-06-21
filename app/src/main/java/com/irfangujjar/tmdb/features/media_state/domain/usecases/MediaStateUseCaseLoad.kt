package com.irfangujjar.tmdb.features.media_state.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel
import com.irfangujjar.tmdb.features.media_state.domain.repos.MediaStateRepo
import com.irfangujjar.tmdb.features.media_state.domain.usecases.params.MediaStateUseCaseLoadParams

class MediaStateUseCaseLoad(
    private val repo: MediaStateRepo
) : UseCase<MediaStateModel, MediaStateUseCaseLoadParams> {
    override suspend fun invoke(params: MediaStateUseCaseLoadParams): MediaStateModel =
        repo.load(
            type = params.type,
            mediaId = params.mediaId,
            sessionId = params.sessionId
        )
}