package com.irfangujjar.tmdb.features.main.celebs.presentation.viewmodels.state

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel

sealed class CelebsState {
    object Loading : CelebsState()
    class Loaded(val celebs: CelebsModel) : CelebsState()
    class ErrorWithCache(val celebs: CelebsModel, val error: ErrorEntity) : CelebsState()
    class Error(val error: ErrorEntity) : CelebsState()
}