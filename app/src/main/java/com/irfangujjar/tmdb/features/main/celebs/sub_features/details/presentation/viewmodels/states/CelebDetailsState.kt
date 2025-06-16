package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.viewmodels.states

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel

sealed interface CelebDetailsState {
    data object Loading : CelebDetailsState
    data class Loaded(
        val celebDetails: CelebDetailsModel
    ) : CelebDetailsState

    data class Error(
        val error: ErrorEntity
    ) : CelebDetailsState
}