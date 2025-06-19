package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadMoviesAndTvShows
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadMoviesAndTvShowsParams
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.states.TMDBMediaListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TMDBMediaListViewModel @Inject constructor(
    private val userSession: UserSession,
    private val useCase: TMDBMediaListUseCaseLoadMoviesAndTvShows
) : ViewModelWithKey<HomeNavKey.TMDBMediaListNavKey>() {

    private val _state = MutableStateFlow<TMDBMediaListState>(TMDBMediaListState.Loading)
    val state: StateFlow<TMDBMediaListState> = _state


    private val _sortBy = mutableStateOf<SortBy>(SortBy.DESC)
    val sortBy: State<SortBy> = _sortBy

    fun updateSortBy(sortBy: SortBy) {
        _sortBy.value = sortBy
        loadDetails()
    }

    override fun doInitial() = loadDetails()

    fun loadDetails() {
        if (_state.value !is TMDBMediaListState.Loading)
            _state.value = TMDBMediaListState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                useCase.invoke(
                    params = TMDBMediaListUseCaseLoadMoviesAndTvShowsParams(
                        userId = userSession.userId!!,
                        category = key!!.category,
                        sessionId = userSession.sessionId!!,
                        sortBy = sortBy.value
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.d(
                        "TMDBMediaListViewModel",
                        "Error Loading ${key!!.category.categoryValue} TMDBList"
                    )
                    _state.value = TMDBMediaListState.Error(error = result.errorEntity)
                }

                is ResultWrapper.Success<TMDBMediaListMoviesAndTvShowsModel> -> {
                    if (result.data.isEmpty())
                        _state.value = TMDBMediaListState.Empty
                    else
                        _state.value = TMDBMediaListState.Loaded(mediaLists = result.data)
                }
            }
        }
    }
}