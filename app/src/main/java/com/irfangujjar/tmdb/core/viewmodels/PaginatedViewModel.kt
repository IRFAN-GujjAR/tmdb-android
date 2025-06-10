package com.irfangujjar.tmdb.core.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.args_holder.NavArgsHolder
import com.irfangujjar.tmdb.core.navigation.screens.HasArgId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class PaginatedViewModel<Data, Params>(
    initialState: Data
) : ViewModelWithErrorAlerts() {

    private val _state: MutableStateFlow<Data> = MutableStateFlow(initialState)
    val state: StateFlow<Data> get() = _state

    private var isLoadingMore = false

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

    companion object {

        inline fun <reified ScreenType : HasArgId> getArgs(
            savedStateHandle: SavedStateHandle,
        ): ScreenType = savedStateHandle.toRoute<ScreenType>()


        inline fun <DataType, reified ScreenType : HasArgId> getInitialState(
            savedStateHandle: SavedStateHandle,
            navArgsHolder: NavArgsHolder<DataType>,
        ): DataType? {
            val argId = getArgs<ScreenType>(savedStateHandle).argId
            return navArgsHolder.getArgData(argsId = argId)
        }
    }
}