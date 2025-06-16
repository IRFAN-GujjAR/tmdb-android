package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesMovieTvShowAppendToResponseConstants
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface TvShowDetailsApi {
    @GET("tv/{${ApiPathFields.TV_ID}}?${APIQueryFields.APPEND_TO_RESPONSE}=${APIQueryFieldValuesMovieTvShowAppendToResponseConstants.ALL}")
    suspend fun loadDetails(@Path(ApiPathFields.TV_ID) tvId: Int): TvShowDetailsModel
}