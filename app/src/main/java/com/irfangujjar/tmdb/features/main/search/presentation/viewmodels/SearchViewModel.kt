package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    var showSearchBar by mutableStateOf(false)
        private set

    var query by mutableStateOf("")
        private set

    fun updateShowSearchBar(value: Boolean) {
        showSearchBar = value
    }

    fun updateQuery(value: String) {
        query = value
    }
}