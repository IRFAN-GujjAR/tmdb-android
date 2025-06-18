package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class EpisodeModel(
    val name: String,
    @SerializedName(JsonKeyNames.AIR_DATE)
    val airDate: String?,
    val overview: String?,
    @SerializedName(JsonKeyNames.STILL_PATH)
    val stillPath: String?,
    @SerializedName(JsonKeyNames.VOTE_COUNT)
    val voteCount: Int?,
    @SerializedName(JsonKeyNames.VOTE_AVERAGE)
    val voteAverage: Double?
) {
    companion object {
        fun dummyData(): EpisodeModel = EpisodeModel(
            name = "Episode 1",
            airDate = "2014-10-02",
            overview = "Business is booming for the Peaky Blinders, as Shelby starts expanding both his legal and illegal operations. He plans for the future, looking at managing a race track in the South. He soon comes across some new adversaries, testing him in very different ways. Back in Birmingham, Shelby's home town is challenged by members of his family reacting to the upturn in their fortunes. A enemy from his past also returns hoping to exact their plan of revenge of biblical proportions.",
            stillPath = "/2JxCri29xSPtPdrrZBWtbehhClL.jpg",
            voteCount = 109,
            voteAverage = 7.986
        )
    }
}
