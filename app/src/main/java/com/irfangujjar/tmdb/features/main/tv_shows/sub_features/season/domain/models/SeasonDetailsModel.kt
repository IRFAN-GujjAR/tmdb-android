package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class SeasonDetailsModel(
    val name: String,
    @SerializedName(JsonKeyNames.AIR_DATE)
    val airDate: String?,
    val overview: String?,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE)
    val voteAverage: Double?,
    val episodes: List<EpisodeModel>
) {
    companion object {
        fun dummyData(): SeasonDetailsModel = SeasonDetailsModel(
            name = "Series 2",
            airDate = "2014-10-02",
            overview = "As the 1920s begin to roar, business is booming for the Peaky Blinders gang. Shelby sets his sights on wider horizons and his meteoric rise brings him into contact with both the upper echelons of society and astonishing new adversaries from London’s criminal world. All will test him to the core, though in very different ways. Meanwhile, Shelby’s home turf faces new challenges as an enemy from his past returns to the city with plans for a revenge of biblical proportions.",
            posterPath = "/m4JPtC3gWUsq0GlfNK3SdgkL9XG.jpg",
            voteAverage = 8.2,
            episodes = List(10) { EpisodeModel.dummyData() }
        )
    }
}