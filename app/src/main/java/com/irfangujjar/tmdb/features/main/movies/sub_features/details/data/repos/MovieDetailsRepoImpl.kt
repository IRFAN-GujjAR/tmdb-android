package com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.repos

import com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.data_sources.MovieDetailsDataSource
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.repos.MovieDetailsRepo

class MovieDetailsRepoImpl(
    private val dataSource: MovieDetailsDataSource
) : MovieDetailsRepo {
    override suspend fun loadMovieDetails(movieId: Int): MovieDetailsModel =
        dataSource.loadMovieDetails(movieId = movieId)
}