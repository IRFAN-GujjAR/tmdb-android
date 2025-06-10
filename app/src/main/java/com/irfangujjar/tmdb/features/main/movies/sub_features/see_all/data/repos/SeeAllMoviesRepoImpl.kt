package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.repos

import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.data_sources.SeeAllMoviesDataSource
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.repos.SeeAllMoviesRepo

class SeeAllMoviesRepoImpl(
    private val dataSource: SeeAllMoviesDataSource
) : SeeAllMoviesRepo {
    override suspend fun loadMovies(
        category: MoviesCategory,
        movieId: Int?,
        pageNo: Int
    ): MoviesListModel = dataSource.loadMovies(
        categories = category,
        movieId = movieId,
        pageNo = pageNo
    )
}