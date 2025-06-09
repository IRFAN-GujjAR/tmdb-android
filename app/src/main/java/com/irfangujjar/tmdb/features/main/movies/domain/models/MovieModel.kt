package com.irfangujjar.tmdb.features.main.movies.domain.models

import JsonKeyNames
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.entities.MediaModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    override val id: Int,
    val title: String,
    @SerializedName(JsonKeyNames.GENRE_IDS) override val genreIds: List<Int>,
    @SerializedName(JsonKeyNames.POSTER_PATH) override val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH) override val backdropPath: String?,
    @SerializedName(JsonKeyNames.VOTE_COUNT) override val voteCount: Int,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE) override val voteAverage: Double,
    @SerializedName(JsonKeyNames.RELEASE_DATE) val releaseDate: String?
) : MediaModel, Parcelable {
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