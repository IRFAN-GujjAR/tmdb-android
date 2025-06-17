package com.irfangujjar.tmdb.features.main.cast_crew.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.nav_args_holder.CastCrewNavArgsHolder
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.viewmodels.states.CastCrewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CastCrewViewModel @Inject constructor(
    private val castCrewNavArgsHolder: CastCrewNavArgsHolder
) : ViewModelWithKey<HomeNavKey.CastCrewNavKey>() {

    private val _state = MutableStateFlow<CastCrewState>(CastCrewState.Initializing)
    val state: StateFlow<CastCrewState> = _state

    override fun doInitial() {
        val credits = castCrewNavArgsHolder.getArgData(key!!.argId)!!
        _state.value = CastCrewState.Initialized(credits = credits)
    }

    override fun onCleared() {
        super.onCleared()
        castCrewNavArgsHolder.removeArg(key!!.argId)
    }
}