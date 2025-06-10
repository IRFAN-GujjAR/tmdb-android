package com.irfangujjar.tmdb.features.main.tv_shows.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class TvShowModel(
    val id: Int,
    val name: String,
    @SerializedName(JsonKeyNames.GENRE_IDS) val genreIds: List<Int>?,
    @SerializedName(JsonKeyNames.POSTER_PATH) val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH) val backdropPath: String?,
    @SerializedName(JsonKeyNames.VOTE_COUNT) val voteCount: Int?,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE) val voteAverage: Double?

) {

    fun isRatingPresent(): Boolean = voteCount != null && voteAverage != null

    companion object {
        fun dummyData(): TvShowModel =
            TvShowModel(
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