package com.irfangujjar.tmdb.features.media_action.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseFavoriteParams

class MediaActionUseCaseFavorite(
    private val repo: MediaActionRepo
) : UseCase<MediaActionModel, MediaActionUseCaseFavoriteParams> {
    override suspend fun invoke(params: MediaActionUseCaseFavoriteParams): MediaActionModel =
        repo.favorite(
            mediaType = params.mediaType,
            mediaId = params.mediaId,
            userId = params.userId,
            sessionId = params.sessionId,
            favorite = params.favorite
        )
}