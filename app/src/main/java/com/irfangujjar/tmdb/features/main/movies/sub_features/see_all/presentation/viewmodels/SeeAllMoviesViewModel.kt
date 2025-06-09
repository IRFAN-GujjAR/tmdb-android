package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder.SeeAllMoviesNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllMoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllMoviesNavArgsHolder
) : ViewModel() {

    private val seeAllMovies = savedStateHandle.toRoute<HomeScreen.SeeAllMovies>()
    val moviesList = navArgsHolder.getArgData(seeAllMovies.argsId)


    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(seeAllMovies.argsId)
    }
}