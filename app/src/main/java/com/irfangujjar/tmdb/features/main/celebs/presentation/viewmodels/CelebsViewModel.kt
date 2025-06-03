package com.irfangujjar.tmdb.features.main.celebs.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.features.main.celebs.domain.usecases.CelebsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.domain.usecases.CelebsUseCaseWatch
import com.irfangujjar.tmdb.features.main.celebs.presentation.viewstate.CelebsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebsViewModel @Inject constructor(
    private val celebsUseCaseWatch: CelebsUseCaseWatch,
    private val celebsUseCaseLoad: CelebsUseCaseLoad
) : ViewModel() {
    private val _state: MutableStateFlow<CelebsState> = MutableStateFlow(CelebsState.Loading)
    val state: StateFlow<CelebsState> = _state

    var isRefreshing by mutableStateOf(false)
        private set

    private val firstEmissionDeferred = CompletableDeferred<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            watchCelebs()
        }
        viewModelScope.launch(Dispatchers.IO) {
            loadCelebs()
        }
    }

    private suspend fun watchCelebs() {
        celebsUseCaseWatch.invoke().collect { celebs ->
            if (celebs != null) {
                _state.value = CelebsState.Loaded(celebs = celebs)
            }
            if (!firstEmissionDeferred.isCompleted)
                firstEmissionDeferred.complete(Unit)
        }
    }

    private suspend fun loadCelebs() {
        val result = safeApiCall {
            if (state.value is CelebsState.Error) {
                _state.value = CelebsState.Loading
            }
            celebsUseCaseLoad.invoke()
        }
        firstEmissionDeferred.await()
        if (result is ResultWrapper.Error) {
            if (state.value is CelebsState.Loaded) {
                _state.value = CelebsState.ErrorWithCache(
                    celebs = (state.value as CelebsState.Loaded).celebs,
                    error = result.errorEntity
                )
            } else {
                _state.value = CelebsState.Error(error = result.errorEntity)
            }
        }
    }

    fun retry() {
        viewModelScope.launch(Dispatchers.IO) {
            loadCelebs()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing = true
            loadCelebs()
            isRefreshing = false
        }
    }
}