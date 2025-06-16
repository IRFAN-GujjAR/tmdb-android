package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models

import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel

data class MovieCreditsModel(
    val cast: List<MovieModel>,
    val crew: List<MovieModel>
) {
    companion object {
        fun dummyDate(): MovieCreditsModel =
            MovieCreditsModel(
                cast = List(20) { MovieModel.dummyData() },
                crew = List(20) { MovieModel.dummyData() }
            )
    }
}

