package com.irfangujjar.tmdb.features.media_action.data.repos

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_action.data.data_sources.MediaActionDataSource
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo

class MediaActionRepoImpl(
    private val dataSource: MediaActionDataSource
) : MediaActionRepo {
    override suspend fun favorite(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        favorite: Boolean
    ): MediaActionModel = dataSource.favorite(
        mediaType = mediaType,
        mediaId = mediaId,
        userId = userId,
        sessionId = sessionId,
        favorite = favorite
    )

    override suspend fun rate(
        mediaType: MediaStateType,
        mediaId: Int,
        rating: Int,
        sessionId: String
    ): MediaActionModel = dataSource.rate(
        mediaType = mediaType,
        mediaId = mediaId,
        rating = rating,
        sessionId = sessionId
    )

    override suspend fun unRate(
        mediaType: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaActionModel = dataSource.unRate(
        mediaType = mediaType,
        mediaId = mediaId,
        sessionId = sessionId
    )

    override suspend fun watchlist(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        watchlist: Boolean
    ): MediaActionModel = dataSource.watchlist(
        mediaType = mediaType,
        mediaId = mediaId,
        userId = userId,
        sessionId = sessionId,
        watchlist = watchlist
    )
}