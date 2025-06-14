package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class CollectionModel(
    val id: Int,
    val name: String,
    @SerializedName(JsonKeyNames.POSTER_PATH)
    val posterPath: String?,
    @SerializedName(JsonKeyNames.BACKDROP_PATH)
    val backdropPath: String?
)
