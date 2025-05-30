package com.irfangujjar.tmdb.features.main.movies.domain.entities

import com.irfangujjar.tmdb.core.entities.MediaEntity

data class MovieEntity(
    override val id: Int,
    val title: String,
    override val genreIds: List<Int>,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val voteCount: Int,
    override val voteAverage: Double,
    val releaseDate: String?
) : MediaEntity {
    companion object {
        fun dummyData(): MovieEntity = MovieEntity(
            id = 238,
            title = "The Godfather",
            genreIds = listOf(18, 80),
            posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            backdropPath = "/tmU7GeKVybMWFButWEGl2M4GeiP.jpg",
            voteCount = 21476,
            voteAverage = 8.686,
            releaseDate = "1972-03-14"
        )
    }
}