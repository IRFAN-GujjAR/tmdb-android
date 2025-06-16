package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.usecases.TvShowDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.usecases.params.TvShowDetailsUseCaseLoadPrams
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.states.TvShowDetailsState
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.nav_args_holder.SeeAllTvShowsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowDetailsViewModel @Inject constructor(
    private val useCase: TvShowDetailsUseCaseLoad,
    private val seeAllTvShowsNavArgsHolder: SeeAllTvShowsNavArgsHolder
) : ViewModelWithKey<HomeNavKey.TvShowDetailsNavKey>() {

    override fun doInitial() = loadTvShowDetails()

    private val _state = MutableStateFlow<TvShowDetailsState>(TvShowDetailsState.Loading)
    val state: StateFlow<TvShowDetailsState> = _state

    fun loadTvShowDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = TvShowDetailsState.Loading
            val result = safeApiCall {
                useCase.invoke(
                    params = TvShowDetailsUseCaseLoadPrams(
                        tvId = key!!.tvId
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.e(
                        "TvShowDetailsViewModel",
                        "Error loading tv show details: ${result.errorEntity}"
                    )
                    _state.value = TvShowDetailsState.Error(
                        error = result.errorEntity
                    )
                }

                is ResultWrapper.Success<TvShowDetailsModel> -> _state.value =
                    TvShowDetailsState.Loaded(
                        tvShowDetails = result.data
                    )
            }
        }
    }

    fun saveSeeAllTvShowsArg(tvShowsList: TvShowsListModel): String =
        seeAllTvShowsNavArgsHolder.saveArgData(tvShowsList)


}