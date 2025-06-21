package com.irfangujjar.tmdb.features.media_state.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MediaStateApi {

    @GET("{${ApiPathFields.MEDIA_STATE_TYPE}}/{${ApiPathFields.MEDIA_ID}}/account_states")
    suspend fun load(
        @Path(ApiPathFields.MEDIA_STATE_TYPE) type: String,
        @Path(ApiPathFields.MEDIA_ID) mediaId: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String
    ): MediaStateModel
}
