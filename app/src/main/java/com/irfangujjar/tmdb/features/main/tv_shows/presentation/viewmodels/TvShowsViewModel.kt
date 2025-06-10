package com.irfangujjar.tmdb.features.main.tv_shows.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases.TvShowsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases.TvShowsUseCaseWatch
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.viewmodels.state.TvShowsState
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.nav_args_holder.SeeAllTvShowsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(
    private val tvShowsUseCaseWatch: TvShowsUseCaseWatch,
    private val tvShowsUseCaseLoad: TvShowsUseCaseLoad,
    private val seeAllTvShowsNavArgsHolder: SeeAllTvShowsNavArgsHolder
) : ViewModelWithErrorAlerts() {
    private val _state: MutableStateFlow<TvShowsState> = MutableStateFlow(TvShowsState.Loading)
    val state: StateFlow<TvShowsState> = _state

    var isRefreshing by mutableStateOf(false)
        private set

    private val firstEmissionDeferred = CompletableDeferred<Unit>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            watchTvShows()
        }
        viewModelScope.launch(Dispatchers.IO) {
            loadTvShows()
        }
    }

    private suspend fun watchTvShows() {
        tvShowsUseCaseWatch.invoke().collect { tvShows ->
            if (tvShows != null) {
                _state.value = TvShowsState.Loaded(tvShows = tvShows)
            }
            if (!firstEmissionDeferred.isCompleted)
                firstEmissionDeferred.complete(Unit)
        }
    }

    private suspend fun loadTvShows() {
        val result = safeApiCall {
            if (state.value is TvShowsState.Error) {
                _state.value = TvShowsState.Loading
            }
            tvShowsUseCaseLoad.invoke()
        }
        firstEmissionDeferred.await()
        if (result is ResultWrapper.Error) {
            if (state.value is TvShowsState.Loaded) {
                showAlert(result.errorEntity.message)
                _state.value = TvShowsState.ErrorWithCache(
                    tvShows = (state.value as TvShowsState.Loaded).tvShows,
                    error = result.errorEntity
                )
            } else {
                _state.value = TvShowsState.Error(error = result.errorEntity)
            }
        }
    }

    fun retry() {
        viewModelScope.launch(Dispatchers.IO) {
            loadTvShows()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing = true
            loadTvShows()
            isRefreshing = false
        }
    }

    fun saveSeeAllTvShowsArg(category: TvShowsCategories, tvShows: TvShowsModel): String =
        when (category) {
            TvShowsCategories.AiringToday -> seeAllTvShowsNavArgsHolder.saveArgData(tvShows.airingToday)
            TvShowsCategories.Trending -> seeAllTvShowsNavArgsHolder.saveArgData(tvShows.trending)
            TvShowsCategories.TopRated -> seeAllTvShowsNavArgsHolder.saveArgData(tvShows.topRated)
            else -> seeAllTvShowsNavArgsHolder.saveArgData(tvShows.popular)
        }
}