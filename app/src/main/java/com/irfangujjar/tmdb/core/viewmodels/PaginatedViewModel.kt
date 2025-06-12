package com.irfangujjar.tmdb.core.viewmodels

import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.args_holder.HasArgId
import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class PaginatedViewModel<D, P>(
    initialState: D,
) : ViewModelWithErrorAlerts() {

    private val _state: MutableStateFlow<D> = MutableStateFlow(initialState)
    val state: StateFlow<D> get() = _state

    private var isLoadingMore = false
    protected var isInitialized = false

    fun initialize(value: D) {
        if (!isInitialized) {
            updateStateValue(value)
            isInitialized = true
        }
    }

    protected abstract fun canLoadMore(current: D): Boolean
    protected abstract fun nextPageParams(current: D): P
    protected abstract suspend fun fetchData(params: P): D
    protected abstract fun mergeData(old: D, new: D): D
    protected fun updateStateValue(value: D) {
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


    companion object {
        fun <DataType> getInitialState(
            argId: String,
            navArgsHolder: NavArgsHolder<DataType>,
        ): DataType? {
            return navArgsHolder.getArgData(argsId = argId)
        }
    }
}

abstract class PaginatedViewModelWithKey<K : HasArgId, D, P>(initialState: D) :
    PaginatedViewModel<D, P>(initialState = initialState) {
    protected var key: K? = null


    protected fun initializeOnlyKey(key: K) {
        if (!isInitialized) {
            this.key = key
            isInitialized = true
        }
    }

    protected fun initializeWithInitialState(
        key: K,
        initialState: D
    ) {
        if (!isInitialized) {
            this.key = key
            updateStateValue(initialState)
            isInitialized = true
        }
    }

    protected fun initializeWithNavArgsHolder(
        key: K,
        navArgsHolder: NavArgsHolder<D>
    ) {
        if (!isInitialized) {
            this.key = key
            val initialData = getInitialState(argId = key.argId, navArgsHolder = navArgsHolder)!!
            updateStateValue(initialData)
            isInitialized = true
        }
    }
}