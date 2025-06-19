package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.nav_args_holder.TvShowCreditsNavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.states.TvShowCreditsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TvShowCreditsViewModel @Inject constructor(
    private val tvShowCreditsNavArgsHolder: TvShowCreditsNavArgsHolder
) : ViewModelWithKey<HomeNavKey.TvShowCreditsNavKey>() {

    private val _state = MutableStateFlow<TvShowCreditsState>(TvShowCreditsState.Initializing)
    val state: StateFlow<TvShowCreditsState> = _state

    override fun doInitial() {
        val tvShowCredits = tvShowCreditsNavArgsHolder.getArgData(key!!.argId)!!
        _state.value = TvShowCreditsState.Initialized(
            credits = tvShowCredits
        )
    }

    override fun onCleared() {
        super.onCleared()
        tvShowCreditsNavArgsHolder.removeArg(key!!.argId)
    }
}