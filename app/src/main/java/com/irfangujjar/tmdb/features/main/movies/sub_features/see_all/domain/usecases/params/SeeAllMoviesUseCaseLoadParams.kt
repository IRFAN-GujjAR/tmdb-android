package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.params

import com.irfangujjar.tmdb.core.ui.util.MoviesCategory

data class SeeAllMoviesUseCaseLoadParams(
    val category: MoviesCategory,
    val movieId: Int?,
    val pageNo: Int
)
