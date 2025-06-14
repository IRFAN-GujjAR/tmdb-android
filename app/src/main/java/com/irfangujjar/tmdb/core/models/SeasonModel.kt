package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class SeasonModel(
    val id: Int,
    @SerializedName(JsonKeyNames.SEASON_NO)
    val seasonNo: Int,
    val name: String,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?
) {
    companion object {
        fun dummyListData(): List<SeasonModel> = listOf(
            SeasonModel(
                id = 178069,
                seasonNo = 0,
                name = "Specials",
                posterPath = "/o5VrKdfN5Xg2pRnQVSqBCi4iNG9.jpg"
            ),
            SeasonModel(
                id = 59914,
                seasonNo = 1,
                name = "Series 1",
                posterPath = "/vPlsKun2WZEsqe3yZTmebxtwElW.jpg"
            ),
            SeasonModel(
                id = 62804,
                seasonNo = 2,
                name = "Series 2",
                posterPath = "/m4JPtC3gWUsq0GlfNK3SdgkL9XG.jpg"
            ),
            SeasonModel(
                id = 72707,
                seasonNo = 3,
                name = "Series 3",
                posterPath = "/b4m8G5j2HkGKh5yeCsV43askQVB.jpg"
            ),
            SeasonModel(
                id = 94653,
                seasonNo = 4,
                name = "Series 4",
                posterPath = "/iAI8YEAZJc2FEj9caLCPK1XCZxL.jpg"
            ),
            SeasonModel(
                id = 129442,
                seasonNo = 5,
                name = "Series 5",
                posterPath = "/kFy0iBDmyC5A5YTsYuNai1Ko0Ja.jpg"
            ),
            SeasonModel(
                id = 217806,
                seasonNo = 6,
                name = "Series 6",
                posterPath = "/vUUqzWa2LnHIVqkaKVlVGkVcZIW.jpg"
            )
        )
    }
}
