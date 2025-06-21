package com.irfangujjar.tmdb.features.media_action.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo
import com.irfangujjar.tmdb.features.media_action.domain.usecases.params.MediaActionUseCaseWatchlistParam

class MediaActionUseCaseWatchlist(
    private val repo: MediaActionRepo
) : UseCase<MediaActionModel, MediaActionUseCaseWatchlistParam> {
    override suspend fun invoke(params: MediaActionUseCaseWatchlistParam): MediaActionModel =
        repo.watchlist(
            mediaType = params.mediaType,
            mediaId = params.mediaId,
            userId = params.userId,
            sessionId = params.sessionId,
            watchlist = params.watchlist
        )
}