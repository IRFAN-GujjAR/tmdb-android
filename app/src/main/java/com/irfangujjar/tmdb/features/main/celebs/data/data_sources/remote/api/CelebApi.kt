package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api

import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CelebApi {

    @GET("trending/person/day")
    suspend fun trendingCelebs(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): CelebsListModel

    @GET("person/popular")
    suspend fun popularCelebs(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): CelebsListModel
}