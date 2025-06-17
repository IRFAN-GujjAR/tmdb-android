package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel

data class CollectionDetailsModel(
    val name: String,
    val overview: String?,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH)
    val backdropPath: String?,
    @SerializedName(JsonKeyNames.MOVIE_COLLECTION_MOVIES)
    val movies: List<MovieModel>
) {
    companion object {
        fun dummyData(): CollectionDetailsModel =
            CollectionDetailsModel(
                name = "The Godfather Collection",
                overview = "Francis Ford Coppola’s trilogy films about the everyday life of a New York mafia family. With its star-studded cast these films would go down in history as some of the greatest Hollywood has ever produced.",
                posterPath = "/zVb22YOMgljCEYbXlvCvEiN4VwT.jpg",
                backdropPath = "/mDMCET9Ens5ANvZAWRpluBsMAtS.jpg",
                movies = List(2) { MovieModel.dummyData() }
            )
    }
}

