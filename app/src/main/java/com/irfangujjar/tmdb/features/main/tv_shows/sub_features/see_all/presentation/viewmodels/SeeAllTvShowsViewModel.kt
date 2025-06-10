package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.SeeAllTvShowsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.params.SeeAllTvShowsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.nav_args_holder.SeeAllTvShowsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllTvShowsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllTvShowsNavArgsHolder,
    private val useCaseLoad: SeeAllTvShowsUseCaseLoad
) : ViewModelWithErrorAlerts() {

    val args = savedStateHandle.toRoute<HomeScreen.SeeAllTvShows>()
    private val _tvShowsListState =
        MutableStateFlow<TvShowsListModel>(navArgsHolder.getArgData(args.argId)!!)
    val tvShowsListState: StateFlow<TvShowsListModel> = _tvShowsListState

    private var isLoadingMore = false

    fun onLoadMore() {
        if (!isLoadingMore && tvShowsListState.value.pageNo < tvShowsListState.value.totalPages) {
            isLoadingMore = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = safeApiCall {
                    useCaseLoad.invoke(
                        params = SeeAllTvShowsUseCaseLoadParams(
                            category = args.category,
                            tvId = args.tvId,
                            pageNo = tvShowsListState.value.pageNo + 1
                        )
                    )
                }
                when (result) {
                    is ResultWrapper.Error -> {
                        showAlert(result.errorEntity.message)
                    }

                    is ResultWrapper.Success<TvShowsListModel> -> {
                        val tvShowsList = TvShowsListModel(
                            pageNo = result.data.pageNo,
                            totalPages = result.data.totalPages,
                            tvShows = tvShowsListState.value.tvShows + result.data.tvShows
                        )
                        _tvShowsListState.value = tvShowsList
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