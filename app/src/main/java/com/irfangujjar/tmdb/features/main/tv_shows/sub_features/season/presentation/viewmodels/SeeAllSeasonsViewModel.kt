package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.nav_args_holder.SeeAllSeasonsNavArgsHolder
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states.SeeAllSeasonsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SeeAllSeasonsViewModel @Inject constructor(
    private val seeAllSeasonsNavArgsHolder: SeeAllSeasonsNavArgsHolder
) : ViewModelWithKey<HomeNavKey.SeeAllSeasonsNavKey>() {

    private val _state = MutableStateFlow<SeeAllSeasonsState>(SeeAllSeasonsState.Initializing)
    val state: StateFlow<SeeAllSeasonsState> = _state

    override fun doInitial() {
        val seasons = seeAllSeasonsNavArgsHolder.getArgData(key!!.argId)!!
        _state.value = SeeAllSeasonsState.Initialized(seasons = seasons)
    }

    override fun onCleared() {
        super.onCleared()
        seeAllSeasonsNavArgsHolder.removeArg(key!!.argId)
    }
}