package com.irfangujjar.tmdb.core.entities

interface MediaEntity {
    val id: Int
    val genreIds: List<Int>
    val posterPath: String?
    val backdropPath: String?
    val voteCount: Int
    val voteAverage: Double
}
