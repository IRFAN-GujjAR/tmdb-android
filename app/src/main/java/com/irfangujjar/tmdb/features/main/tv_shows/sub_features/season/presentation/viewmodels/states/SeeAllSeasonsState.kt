package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.models.SeasonModel

sealed interface SeeAllSeasonsState {
    data object Initializing : SeeAllSeasonsState
    data class Initialized(val seasons: List<SeasonModel>) : SeeAllSeasonsState
}