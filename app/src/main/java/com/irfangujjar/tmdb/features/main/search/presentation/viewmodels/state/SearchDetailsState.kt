package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel

sealed class SearchDetailsState {
    object Loading : SearchDetailsState()
    class Loaded(val searchDetails: SearchDetailsModel) : SearchDetailsState()
    class Error(val error: ErrorEntity) : SearchDetailsState()
}