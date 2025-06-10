package com.irfangujjar.tmdb.features.main.movies.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class MovieModel(
    val id: Int,
    val title: String,
    @SerializedName(JsonKeyNames.GENRE_IDS) val genreIds: List<Int>?,
    @SerializedName(JsonKeyNames.POSTER_PATH) val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH) val backdropPath: String?,
    @SerializedName(JsonKeyNames.VOTE_COUNT) val voteCount: Int?,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE) val voteAverage: Double?,
    @SerializedName(JsonKeyNames.RELEASE_DATE) val releaseDate: String?
) {
    fun isRatingPresent(): Boolean = voteCount != null && voteAverage != null


    companion object {
        fun dummyData(): MovieModel = MovieModel(
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