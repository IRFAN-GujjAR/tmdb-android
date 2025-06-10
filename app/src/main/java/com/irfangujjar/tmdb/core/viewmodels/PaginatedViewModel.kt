package com.irfangujjar.tmdb.core.viewmodels

import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class PaginatedViewModel<Data, Params> : ViewModelWithErrorAlerts() {

    private val _state: MutableStateFlow<Data> = MutableStateFlow(initialState())
    val state: StateFlow<Data> get() = _state

    private var isLoadingMore = false

    protected abstract fun initialState(): Data
    protected abstract fun canLoadMore(current: Data): Boolean
    protected abstract fun nextPageParams(current: Data): Params
    protected abstract suspend fun fetchData(params: Params): Data
    protected abstract fun mergeData(old: Data, new: Data): Data
    protected fun updateStateValue(value: Data) {
        _state.value = value
    }

    fun loadMore() {
        if (isLoadingMore || !canLoadMore(_state.value)) return

        isLoadingMore = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                fetchData(nextPageParams(_state.value))
            }
            when (result) {
                is ResultWrapper.Error -> showAlert(result.errorEntity.message)
                is ResultWrapper.Success -> _state.value =
                    mergeData(_state.value, result.data)
            }
        }.invokeOnCompletion {
            isLoadingMore = false
        }
    }
}