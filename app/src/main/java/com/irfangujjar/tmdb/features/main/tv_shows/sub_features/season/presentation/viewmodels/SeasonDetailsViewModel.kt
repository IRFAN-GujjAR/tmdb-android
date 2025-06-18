package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.usecases.SeasonDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.usecases.params.SeasonDetailsUseCaseLoadPrams
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states.SeasonDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonDetailsViewModel @Inject constructor(
    private val useCaseLoad: SeasonDetailsUseCaseLoad,
) : ViewModelWithKey<HomeNavKey.SeasonDetailsNavKey>() {

    private val _state = MutableStateFlow<SeasonDetailsState>(SeasonDetailsState.Loading)
    val state: StateFlow<SeasonDetailsState> = _state

    override fun doInitial() = loadDetails()

    fun loadDetails() {
        if (state.value !is SeasonDetailsState.Loading)
            _state.value = SeasonDetailsState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            val result = safeApiCall {
                useCaseLoad.invoke(
                    params = SeasonDetailsUseCaseLoadPrams(
                        tvId = key!!.tvId,
                        seasonNo = key!!.seasonNo
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.d(
                        "SeasonDetailsViewModel",
                        "Error Loading Season Details : ${result.errorEntity.message}"
                    )
                    _state.value = SeasonDetailsState.Error(
                        error = result.errorEntity
                    )
                }

                is ResultWrapper.Success<SeasonDetailsModel> -> {
                    _state.value = SeasonDetailsState.Loaded(
                        seasonDetails = result.data
                    )
                }
            }
        }
    }


}