package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.SeeAllCelebsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.params.SeeAllCelebsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.nav_args_holder.SeeAllCelebsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllCelebsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllCelebsNavArgsHolder,
    private val useCaseLoad: SeeAllCelebsUseCaseLoad
) : ViewModelWithErrorAlerts() {
    val args = savedStateHandle.toRoute<HomeScreen.SeeAllCelebs>()
    private val _celebsListState =
        MutableStateFlow<CelebsListModel>(navArgsHolder.getArgData(args.argId)!!)
    val celebsListState: StateFlow<CelebsListModel> = _celebsListState

    private var isLoadingMore = false

    fun onLoadMore() {
        if (!isLoadingMore && celebsListState.value.pageNo < celebsListState.value.totalPages) {
            isLoadingMore = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = safeApiCall {
                    useCaseLoad.invoke(
                        params = SeeAllCelebsUseCaseLoadParams(
                            category = args.category,
                            pageNo = celebsListState.value.pageNo + 1
                        )
                    )
                }
                when (result) {
                    is ResultWrapper.Error -> {
                        showAlert(result.errorEntity.message)
                    }

                    is ResultWrapper.Success<CelebsListModel> -> {
                        val celebsList = CelebsListModel(
                            pageNo = result.data.pageNo,
                            totalPages = result.data.totalPages,
                            celebrities = celebsListState.value.celebrities + result.data.celebrities
                        )
                        _celebsListState.value = celebsList
                    }
                }
            }.invokeOnCompletion {
                isLoadingMore = false
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(args.argId)
    }

}