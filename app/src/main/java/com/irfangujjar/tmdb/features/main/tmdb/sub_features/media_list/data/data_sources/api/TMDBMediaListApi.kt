package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBMediaListApi {
    @GET("account/{${ApiPathFields.USER_ID}}/{category}/movies")
    suspend fun loadMediaListMovies(
        @Path(ApiPathFields.USER_ID) userId: Int,
        @Path("category") category: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
        @Query(APIQueryFields.SORT_BY) sortBy: String
    ): MoviesListModel

    @GET("account/{${ApiPathFields.USER_ID}}/{category}/tv")
    suspend fun loadMediaListTvShows(
        @Path(ApiPathFields.USER_ID) userId: Int,
        @Path("category") category: String,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title,
        @Query(APIQueryFields.PAGE) pageNo: Int,
        @Query(APIQueryFields.SESSION_ID) sessionId: String,
        @Query(APIQueryFields.SORT_BY) sortBy: String
    ): TvShowsListModel
}
