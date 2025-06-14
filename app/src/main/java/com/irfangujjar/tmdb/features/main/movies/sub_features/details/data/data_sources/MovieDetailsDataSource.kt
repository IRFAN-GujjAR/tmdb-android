package com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.data_sources

import com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.data_sources.api.MovieDetailsApi
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel

interface MovieDetailsDataSource {
    suspend fun loadMovieDetails(movieId: Int): MovieDetailsModel
}

class MovieDetailsDataSourceImpl(
    private val api: MovieDetailsApi
) : MovieDetailsDataSource {

    override suspend fun loadMovieDetails(movieId: Int): MovieDetailsModel =
        api.loadDetails(movieId = movieId)
}