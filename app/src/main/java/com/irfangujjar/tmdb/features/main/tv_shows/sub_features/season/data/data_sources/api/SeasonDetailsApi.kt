package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeasonDetailsApi {
    @GET("tv/{${ApiPathFields.TV_ID}}/season/{${ApiPathFields.SEASON_NO}}")
    suspend fun load(
        @Path(ApiPathFields.TV_ID) tvId: Int,
        @Path(ApiPathFields.SEASON_NO) seasonNo: Int,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title
    ): SeasonDetailsModel
}
