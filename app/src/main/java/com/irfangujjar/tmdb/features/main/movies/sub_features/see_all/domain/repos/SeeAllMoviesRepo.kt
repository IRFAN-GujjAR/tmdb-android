package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.repos

import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel

interface SeeAllMoviesRepo {
    suspend fun loadMovies(
        category: MoviesCategories,
        movieId: Int?,
        pageNo: Int
    ): MoviesListModel
}