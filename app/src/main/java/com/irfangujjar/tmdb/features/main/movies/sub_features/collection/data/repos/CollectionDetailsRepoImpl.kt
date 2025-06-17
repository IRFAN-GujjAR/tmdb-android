package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.repos

import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources.CollectionDetailsDataSource
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.repos.CollectionDetailsRepo

class CollectionDetailsRepoImpl(
    private val dataSource: CollectionDetailsDataSource
) : CollectionDetailsRepo {
    override suspend fun loadDetails(collectionId: Int): CollectionDetailsModel =
        dataSource.loadDetails(collectionId = collectionId)
}