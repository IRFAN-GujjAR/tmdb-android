package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.params

import com.irfangujjar.tmdb.core.ui.util.CelebsCategory

data class SeeAllCelebsUseCaseLoadParams(
    val category: CelebsCategory,
    val pageNo: Int
)