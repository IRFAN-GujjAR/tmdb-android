package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel

sealed class TrendingSearchState {
    object Loading : TrendingSearchState()
    class Loaded(val trendingSearch: SearchModel) : TrendingSearchState()
    class Error(val error: ErrorEntity) : TrendingSearchState()
    class ErrorWithCache(val trendingSearch: SearchModel, val error: ErrorEntity) :
        TrendingSearchState()
}