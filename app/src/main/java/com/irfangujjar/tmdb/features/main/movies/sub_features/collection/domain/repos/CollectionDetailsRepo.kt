package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.repos

import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel

interface CollectionDetailsRepo {
    suspend fun loadDetails(collectionId: Int): CollectionDetailsModel
}