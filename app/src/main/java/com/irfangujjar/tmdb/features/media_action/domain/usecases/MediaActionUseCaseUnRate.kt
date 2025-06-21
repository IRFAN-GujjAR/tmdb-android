package com.irfangujjar.tmdb.features.media_action.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseUnRateParams

class MediaActionUseCaseUnRate(
    private val repo: MediaActionRepo
) : UseCase<MediaActionModel, MediaActionUseCaseUnRateParams> {
    override suspend fun invoke(params: MediaActionUseCaseUnRateParams): MediaActionModel =
        repo.unRate(
            mediaType = params.mediaType,
            mediaId = params.mediaId,
            sessionId = params.sessionId
        )
}