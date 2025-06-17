package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.repos.CollectionDetailsRepo
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.usecases.params.CollectionDetailsUseCaseLoadParams

class CollectionDetailsUseCaseLoad(
    private val repo: CollectionDetailsRepo
) : UseCase<CollectionDetailsModel, CollectionDetailsUseCaseLoadParams> {
    override suspend fun invoke(params: CollectionDetailsUseCaseLoadParams): CollectionDetailsModel =
        repo.loadDetails(collectionId = params.collectionId)
}