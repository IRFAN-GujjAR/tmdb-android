package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel

sealed class SearchSuggestionState {
    object Idle : SearchSuggestionState()
    object Loading : SearchSuggestionState()
    class Loaded(val searchSuggestion: SearchModel) : SearchSuggestionState()
    class Error(val error: ErrorEntity) : SearchSuggestionState()
}