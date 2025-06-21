package com.irfangujjar.tmdb.features.media_action.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.FavoriteMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.RateMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params.WatchlistMediaActionBodyParam
import com.irfangujjar.tmdb.features.media_action.domain.models.MediaActionModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaActionApi {
    @POST("account/{${ApiPathFields.USER_ID}}/favorite")
    suspend fun favorite(
        @Path(ApiPathFields.USER_ID) userId: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
        @Body bodyParam: FavoriteMediaActionBodyParam
    ): MediaActionModel

    @POST("{${ApiPathFields.MEDIA_STATE_TYPE}}/{${ApiPathFields.MEDIA_ID}}/rating")
    suspend fun rate(
        @Path(ApiPathFields.MEDIA_STATE_TYPE) type: String,
        @Path(ApiPathFields.MEDIA_ID) mediaId: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
        @Body bodyParam: RateMediaActionBodyParam
    ): MediaActionModel

    @DELETE("{${ApiPathFields.MEDIA_STATE_TYPE}}/{${ApiPathFields.MEDIA_ID}}/rating")
    suspend fun unRate(
        @Path(ApiPathFields.MEDIA_STATE_TYPE) type: String,
        @Path(ApiPathFields.MEDIA_ID) mediaId: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
    ): MediaActionModel

    @POST("account/{${ApiPathFields.USER_ID}}/watchlist")
    suspend fun watchlist(
        @Path(ApiPathFields.USER_ID) userId: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
        @Body bodyParam: WatchlistMediaActionBodyParam
    ): MediaActionModel
}
