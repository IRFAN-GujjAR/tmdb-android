package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources.api

import com.irfangujjar.tmdb.core.api.path.ApiPathFields
import com.irfangujjar.tmdb.core.api.query.APIQueryFields
import com.irfangujjar.tmdb.core.api.query.values.APIQueryFieldValuesLanguage
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionDetailsApi {

    @GET("collection/{${ApiPathFields.COLLECTION_ID}}")
    suspend fun load(
        @Path(ApiPathFields.COLLECTION_ID) collectionId: Int,
        @Query(APIQueryFields.LANGUAGE) language: String = APIQueryFieldValuesLanguage.ENGLISH.title
    ): CollectionDetailsModel
}

