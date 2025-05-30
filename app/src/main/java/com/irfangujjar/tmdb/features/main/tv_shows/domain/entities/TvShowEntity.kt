package com.irfangujjar.tmdb.features.main.tv_shows.domain.entities

import com.irfangujjar.tmdb.core.entities.MediaEntity

data class TvShowEntity(
    override val id: Int,
    val name: String,
    override val genreIds: List<Int>,
    override val posterPath: String?,
    override val backdropPath: String?,
    override val voteCount: Int,
    override val voteAverage: Double

) : MediaEntity {
    companion object {
        fun dummyData(): TvShowEntity =
            TvShowEntity(
                id = 60574,
                name = "Peaky Blinders",
                genreIds = listOf(18, 80),
                posterPath = "/vUUqzWa2LnHIVqkaKVlVGkVcZIW.jpg",
                backdropPath = "/or7wKwv1IT6LEOktt395O5qi7e.jpg",
                voteCount = 10376,
                voteAverage = 8.531
            )
    }
}