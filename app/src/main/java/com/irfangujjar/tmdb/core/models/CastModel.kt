package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie

data class CastModel(
    val id: Int,
    val character: String,
    val name: String,
    val gender: Int?,
    val order: Int?,
    @SerializedName(JsonKeyNames.PROFILE_PATH)
    val profilePath: String?
) {
    companion object {
        fun dummyData(type: MediaType): CastModel = if (type.isMovie()) CastModel(
            id = 3084,
            character = "Don Vito Corleone",
            name = "Marlon Brando",
            gender = 2,
            order = 0,
            profilePath = "/vklkhX4QlRKnEG8ylhWzoBdcuev.jpg"
        ) else CastModel(
            id = 2037,
            character = "Thomas Shelby",
            name = "Cillian Murphy",
            gender = 2,
            order = 0,
            profilePath = "/llkbyWKwpfowZ6C8peBjIV9jj99.jpg"
        )
    }
}
