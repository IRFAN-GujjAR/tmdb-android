package com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.repos

import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel

interface MovieDetailsRepo {
    suspend fun loadMovieDetails(movieId: Int): MovieDetailsModel
}