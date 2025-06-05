package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseLoad
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseWatch
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchState
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.TrendingSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trendingUseCaseLoad: TrendingSearchUseCaseLoad,
    private val trendingUseCaseWatch: TrendingSearchUseCaseWatch
) : ViewModel() {

    var showSearchBar by mutableStateOf(false)
        private set

    fun updateShowSearchBar(value: Boolean) {
        showSearchBar = value
    }

    var query by mutableStateOf("")
        private set

    fun updateQuery(value: String) {
        query = value
    }

    private val _state = MutableStateFlow<SearchState>(SearchState.Trending)
    val state: StateFlow<SearchState> = _state

    private val firstEmissionDeferred = CompletableDeferred<Unit>()

    private val _trendingState = MutableStateFlow<TrendingSearchState>(TrendingSearchState.Loading)
    val trendingState: StateFlow<TrendingSearchState> = _trendingState

    var isTrendingSearchRefreshing by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {
            watchTrendingSearch()
        }
        viewModelScope.launch(Dispatchers.IO) {
            loadTrendingSearch()
        }
    }

    private suspend fun watchTrendingSearch() {
        trendingUseCaseWatch.invoke().collect { search ->
            if (search != null) {
                _trendingState.value = TrendingSearchState.Loaded(trendingSearch = search)
            }
            if (!firstEmissionDeferred.isCompleted)
                firstEmissionDeferred.complete(Unit)
        }
    }

    private suspend fun loadTrendingSearch() {
        val result = safeApiCall {
            if (trendingState.value is TrendingSearchState.Error) {
                _trendingState.value = TrendingSearchState.Loading
            }
            trendingUseCaseLoad.invoke()
        }
        firstEmissionDeferred.await()
        if (result is ResultWrapper.Error) {
            if (trendingState.value is TrendingSearchState.Loaded) {
                _trendingState.value = TrendingSearchState.ErrorWithCache(
                    trendingSearch = (trendingState.value as TrendingSearchState.Loaded).trendingSearch,
                    error = result.errorEntity
                )
            } else {
                _trendingState.value = TrendingSearchState.Error(error = result.errorEntity)
            }
        }
    }

    fun retryTrendingSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            loadTrendingSearch()
        }
    }

    fun refreshTrendingSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            isTrendingSearchRefreshing = true
            loadTrendingSearch()
            isTrendingSearchRefreshing = false
        }
    }
}