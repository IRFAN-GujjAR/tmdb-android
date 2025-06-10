package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.params

import com.irfangujjar.tmdb.core.ui.util.MoviesCategories

data class SeeAllMoviesUseCaseLoadParams(
    val category: MoviesCategories,
    val movieId: Int?,
    val pageNo: Int
)
