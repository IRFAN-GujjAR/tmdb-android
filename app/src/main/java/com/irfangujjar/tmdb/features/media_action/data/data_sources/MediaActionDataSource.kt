package com.irfangujjar.tmdb.features.media_action.data.data_sources

import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.MediaActionApi
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.FavoriteMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.RateMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.WatchlistMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel

interface MediaActionDataSource {
    suspend fun favorite(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        favorite: Boolean
    ): MediaActionModel

    suspend fun rate(
        mediaType: MediaStateType,
        mediaId: Int,
        rating: Int,
        sessionId: String
    ): MediaActionModel

    suspend fun unRate(
        mediaType: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaActionModel

    suspend fun watchlist(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        watchlist: Boolean
    ): MediaActionModel
}

class MediaActionDataSourceImpl(
    private val api: MediaActionApi
) : MediaActionDataSource {
    override suspend fun favorite(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        favorite: Boolean
    ): MediaActionModel = api.favorite(
        userId = userId,
        sessionId = sessionId,
        bodyParam = FavoriteMediaActionBodyParam(
            mediaType = mediaType.value,
            mediaId = mediaId,
            favorite = favorite
        )
    )

    override suspend fun rate(
        mediaType: MediaStateType,
        mediaId: Int,
        rating: Int,
        sessionId: String
    ): MediaActionModel = api.rate(
        type = mediaType.value,
        mediaId = mediaId,
        sessionId = sessionId,
        bodyParam = RateMediaActionBodyParam(value = rating)
    )

    override suspend fun unRate(
        mediaType: MediaStateType,
        mediaId: Int,
        sessionId: String
    ): MediaActionModel = api.unRate(
        type = mediaType.value, mediaId = mediaId,
        sessionId = sessionId
    )

    override suspend fun watchlist(
        mediaType: MediaStateType,
        mediaId: Int,
        userId: Int,
        sessionId: String,
        watchlist: Boolean
    ): MediaActionModel = api.watchlist(
        userId = userId,
        sessionId = sessionId,
        bodyParam = WatchlistMediaActionBodyParam(
            mediaType = mediaType.value,
            mediaId = mediaId,
            watchlist = watchlist
        )
    )

}