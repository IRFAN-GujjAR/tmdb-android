package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api

import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto.SearchResponse
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("trending/all/day")
    suspend fun trendingSearch(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
    ): SearchResponse

    @GET("search/multi")
    suspend fun multiSearch(
        @Query(APIQueryFields.SEARCH_QUERY) query: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): SearchResponse

    @GET("search/movie")
    suspend fun searchMovies(
        @Query(APIQueryFields.SEARCH_QUERY) query: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("search/tv")
    suspend fun searchTvShows(
        @Query(APIQueryFields.SEARCH_QUERY) query: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): TvShowsListModel

    @GET("search/person")
    suspend fun searchCelebs(
        @Query(APIQueryFields.SEARCH_QUERY) query: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): CelebsListModel
}
