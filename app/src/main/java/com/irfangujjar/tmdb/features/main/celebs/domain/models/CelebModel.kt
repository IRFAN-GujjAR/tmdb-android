package com.irfangujjar.tmdb.features.main.celebs.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class CelebModel(
    val id: Int,
    val name: String,
    @SerializedName(JsonKeyNames.KNOWN_FOR_DEPT) val knownFor: String?,
    @SerializedName(JsonKeyNames.PROFILE_PATH) val profilePath: String?
)