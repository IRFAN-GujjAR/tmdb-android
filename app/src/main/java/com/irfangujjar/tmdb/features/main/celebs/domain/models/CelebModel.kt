package com.irfangujjar.tmdb.features.main.celebs.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class CelebModel(
    val id: Int,
    val name: String,
    @SerializedName(JsonKeyNames.KNOWN_FOR_DEPT) val knownFor: String?,
    @SerializedName(JsonKeyNames.PROFILE_PATH) val profilePath: String?
) {
    companion object {
        fun dummyData(): CelebModel = CelebModel(
            id = 3894,
            name = "Christian Bale",
            knownFor = "Acting",
            profilePath = "/7Pxez9J8fuPd2Mn9kex13YALrCQ.jpg"
        )
    }
}