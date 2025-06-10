package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.error.ErrorType
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.usecases.SearchDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.search.domain.usecases.SearchSuggestionsUseCaseLoad
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseLoad
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseWatch
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchDetailsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchSuggestionUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchDetailsState
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchState
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchSuggestionState
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.TrendingSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.cancellation.CancellationException

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trendingUseCaseLoad: TrendingSearchUseCaseLoad,
    private val trendingUseCaseWatch: TrendingSearchUseCaseWatch,
    private val searchSuggestionsUseCaseLoad: SearchSuggestionsUseCaseLoad,
    private val searchDetailsUseCaseLoad: SearchDetailsUseCaseLoad
) : ViewModelWithErrorAlerts() {

    var showSearchBar by mutableStateOf(false)
        private set

    var query by mutableStateOf("")
        private set

    private val _state = MutableStateFlow<SearchState>(SearchState.Trending)
    val state: StateFlow<SearchState> = _state

    private val firstEmissionDeferred = CompletableDeferred<Unit>()

    private val _trendingState = MutableStateFlow<TrendingSearchState>(TrendingSearchState.Loading)
    val trendingState: StateFlow<TrendingSearchState> = _trendingState

    var isTrendingSearchRefreshing by mutableStateOf(false)
        private set

    private val _suggestionsState =
        MutableStateFlow<SearchSuggestionState>(SearchSuggestionState.Idle)
    val suggestionsState: StateFlow<SearchSuggestionState> = _suggestionsState

    private var suggestionsJob: Job? = null

    private val _detailsState = MutableStateFlow<SearchDetailsState>(SearchDetailsState.Loading)
    val detailsState: StateFlow<SearchDetailsState> = _detailsState

    private var detailsJob: Job? = null


    fun updateShowSearchBar(value: Boolean) {
        showSearchBar = value
        if (!showSearchBar) {
            _state.value = SearchState.Trending
            query = ""
            resetSuggestionAndDetailsState()
        } else {
            _state.value = SearchState.Suggestions
        }
    }

    fun updateQuery(value: String) {
        query = value
        if (query.isEmpty()) {
            resetSuggestionAndDetailsState()
        } else {
            loadSuggestions()
        }
    }

    private fun resetSuggestionAndDetailsState() {
        suggestionsJob?.cancel()
        detailsJob?.cancel()
        _suggestionsState.value = SearchSuggestionState.Idle
        _detailsState.value = SearchDetailsState.Loading
    }

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
                showAlert(result.errorEntity.message)
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

    private fun loadSuggestions() {
        detailsJob?.cancel()
        suggestionsJob?.cancel()
        suggestionsJob = viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                _suggestionsState.value = SearchSuggestionState.Loading
                searchSuggestionsUseCaseLoad.invoke(params = SearchSuggestionUseCaseLoadParams(query))
            }
            when (result) {
                is ResultWrapper.Error -> {
                    val errorType = result.errorEntity.type
                    if (!(errorType is ErrorType.Unknown && errorType.e is CancellationException)) {
                        _suggestionsState.value = SearchSuggestionState.Error(
                            error = result.errorEntity
                        )
                    }
                }

                is ResultWrapper.Success<SearchModel> -> {
                    _suggestionsState.value = SearchSuggestionState.Loaded(
                        searchSuggestion = result.data
                    )
                }
            }
        }
    }

    fun onTrendingItemSelected(value: String) {
        updateShowSearchBar(true)
        updateQuery(value)
    }

    fun onSuggestionItemSelected(value: String) {
        query = value
        _state.value = SearchState.Details
        loadDetails()
    }


    fun retrySearchSuggestions() {
        loadSuggestions()
    }

    fun retrySearchDetails() {
        loadDetails()
    }

    fun onSearch() {
        if (query.isNotEmpty() && query.isNotBlank()) {
            _state.value = SearchState.Details
            loadDetails()
        }
    }

    private fun loadDetails() {
        suggestionsJob?.cancel()
        detailsJob?.cancel()
        detailsJob = viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                _detailsState.value = SearchDetailsState.Loading
                searchDetailsUseCaseLoad.invoke(params = SearchDetailsUseCaseLoadParams(query = query))
            }
            when (result) {
                is ResultWrapper.Error -> {
                    val errorType = result.errorEntity.type
                    if (!(errorType is ErrorType.Unknown && errorType.e is CancellationException)) {
                        _detailsState.value =
                            SearchDetailsState.Error(error = result.errorEntity)
                    }
                }

                is ResultWrapper.Success<SearchDetailsModel> -> _detailsState.value =
                    SearchDetailsState.Loaded(searchDetails = result.data)
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        suggestionsJob?.cancel()
    }


}