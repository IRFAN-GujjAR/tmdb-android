package com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api

import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowApi {
    @GET("tv/airing_today")
    suspend fun airingTodayTvShows(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.name,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): TvShowsListModel

    @GET("trending/tv/day")
    suspend fun trendingTvShows(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.name,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): TvShowsListModel

    @GET("tv/top_rated")
    suspend fun topRatedTvShows(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.name,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): TvShowsListModel

    @GET("tv/popular")
    suspend fun popularTvShows(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.name,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): TvShowsListModel
}