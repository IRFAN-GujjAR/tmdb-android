package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel

sealed interface TMDBMediaListState {
    data object Loading : TMDBMediaListState
    data class Loaded(val mediaLists: TMDBMediaListMoviesAndTvShowsModel) : TMDBMediaListState
    data class Error(val error: ErrorEntity) : TMDBMediaListState
    data object Empty : TMDBMediaListState
}