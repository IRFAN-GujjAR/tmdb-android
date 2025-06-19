package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.nav_args_holder.MovieCreditsNavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.nav_args_holder.TvShowCreditsNavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.MovieCreditsModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.TvShowCreditsModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.usecases.CelebDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.usecases.params.CelebDetailsUseCaseLoadPrams
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.viewmodels.states.CelebDetailsState
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.nav_args_holder.SeeAllCelebsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CelebDetailsViewModel @Inject constructor(
    private val useCase: CelebDetailsUseCaseLoad,
    private val seeAllCelebsNavArgsHolder: SeeAllCelebsNavArgsHolder,
    private val movieCreditsNavArgsHolder: MovieCreditsNavArgsHolder,
    private val tvShowCreditsNavArgsHolder: TvShowCreditsNavArgsHolder,
) : ViewModelWithKey<HomeNavKey.CelebDetailsNavKey>() {


    override fun doInitial() = loadCelebDetails()

    private val _state = MutableStateFlow<CelebDetailsState>(CelebDetailsState.Loading)
    val state: StateFlow<CelebDetailsState> = _state


    fun loadCelebDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = CelebDetailsState.Loading
            val result = safeApiCall {
                useCase.invoke(
                    params = CelebDetailsUseCaseLoadPrams(
                        celebId = key!!.celebId
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.d(
                        "CelebDetailsViewModel",
                        "Error loading celeb details: ${result.errorEntity}"
                    )
                    _state.value = CelebDetailsState.Error(
                        error = result.errorEntity
                    )
                }

                is ResultWrapper.Success<CelebDetailsModel> -> _state.value =
                    CelebDetailsState.Loaded(
                        celebDetails = result.data
                    )
            }
        }
    }

    fun saveSeeAllCelebsArg(celebsList: CelebsListModel): String =
        seeAllCelebsNavArgsHolder.saveArgData(celebsList)

    fun saveMovieCredits(movieCredits: MovieCreditsModel): String =
        movieCreditsNavArgsHolder.saveArgData(movieCredits)

    fun saveTvShowCredits(tvShowCredits: TvShowCreditsModel): String =
        tvShowCreditsNavArgsHolder.saveArgData(tvShowCredits)
}