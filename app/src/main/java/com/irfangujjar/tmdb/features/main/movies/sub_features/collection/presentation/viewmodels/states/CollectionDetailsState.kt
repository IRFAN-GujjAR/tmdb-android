package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel

sealed interface CollectionDetailsState {
    data object Loading : CollectionDetailsState
    data class Loaded(val collectionDetails: CollectionDetailsModel) : CollectionDetailsState
    data class Error(val error: ErrorEntity) : CollectionDetailsState
}