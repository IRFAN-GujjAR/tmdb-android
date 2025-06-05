package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state

sealed class SearchState {
    object Trending : SearchState()
    object Suggestions : SearchState()
    object Details : SearchState()
}