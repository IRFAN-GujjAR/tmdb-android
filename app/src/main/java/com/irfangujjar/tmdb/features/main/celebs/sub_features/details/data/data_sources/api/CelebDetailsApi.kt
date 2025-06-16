package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CelebDetailsApi {
    @GET("person/{${ApiPathFields.CELEB_ID}}?${APIQueryFields.APPEND_TO_RESPONSE}=movie_credits%2Ctv_credits")
    suspend fun loadDetails(
        @Path(ApiPathFields.CELEB_ID) celebId: Int,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title
    ): CelebDetailsModel
}