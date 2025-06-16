package com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    suspend fun popularMovies(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("movie/now_playing")
    suspend fun inTheatreMovies(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("trending/movie/day")
    suspend fun trendingMovies(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("movie/upcoming")
    suspend fun upcomingMovies(
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("movie/{${ApiPathFields.MOVIE_ID}}/recommendations")
    suspend fun recommendedMovies(
        @Path(ApiPathFields.MOVIE_ID) movieId: Int,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel

    @GET("movie/{${ApiPathFields.MOVIE_ID}}/similar")
    suspend fun similarMovies(
        @Path(ApiPathFields.MOVIE_ID) movieId: Int,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int
    ): MoviesListModel
}