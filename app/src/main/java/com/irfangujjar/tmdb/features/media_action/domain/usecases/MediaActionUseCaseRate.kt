package com.irfangujjar.tmdb.features.media_action.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseRateParams

class MediaActionUseCaseRate(
    private val repo: MediaActionRepo
) : UseCase<MediaActionModel, MediaActionUseCaseRateParams> {
    override suspend fun invoke(params: MediaActionUseCaseRateParams): MediaActionModel =
        repo.rate(
            mediaType = params.mediaType,
            mediaId = params.mediaId,
            rating = params.rating,
            sessionId = params.sessionId
        )
}