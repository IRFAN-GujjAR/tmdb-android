package com.irfangujjar.tmdb.features.main.tv_shows.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.entities.MediaModel

data class TvShowModel(
    override val id: Int,
    val name: String,
    @SerializedName(JsonKeyNames.GENRE_IDS) override val genreIds: List<Int>,
    @SerializedName(JsonKeyNames.POSTER_PATH) override val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH) override val backdropPath: String?,
    @SerializedName(JsonKeyNames.VOTE_COUNT) override val voteCount: Int,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE) override val voteAverage: Double

) : MediaModel {
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