package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.nav_args_holder.MovieCreditsNavArgsHolder
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.states.MovieCreditsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MovieCreditsViewModel @Inject constructor(
    private val movieCreditsNavArgsHolder: MovieCreditsNavArgsHolder
) : ViewModelWithKey<HomeNavKey.MovieCreditsNavKey>() {

    private val _state = MutableStateFlow<MovieCreditsState>(MovieCreditsState.Initializing)
    val state: StateFlow<MovieCreditsState> = _state

    override fun doInitial() {
        val movieCredits = movieCreditsNavArgsHolder.getArgData(key!!.argId)!!
        _state.value = MovieCreditsState.Initialized(
            credits = movieCredits
        )
    }

    override fun onCleared() {
        super.onCleared()
        movieCreditsNavArgsHolder.removeArg(key!!.argId)
    }
}