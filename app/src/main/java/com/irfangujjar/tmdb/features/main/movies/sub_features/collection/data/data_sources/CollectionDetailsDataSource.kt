package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources

import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources.api.CollectionDetailsApi
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel

interface CollectionDetailsDataSource {
    suspend fun loadDetails(collectionId: Int): CollectionDetailsModel
}

class CollectionDetailsDataSourceImpl(
    private val api: CollectionDetailsApi
) : CollectionDetailsDataSource {
    override suspend fun loadDetails(collectionId: Int): CollectionDetailsModel =
        api.load(collectionId = collectionId)
}