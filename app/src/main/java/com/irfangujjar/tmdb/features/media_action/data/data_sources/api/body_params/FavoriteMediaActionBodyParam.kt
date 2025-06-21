package com.irfangujjar.tmdb.features.media_action.data.data_sources.api.body_params

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class FavoriteMediaActionBodyParam(
    @SerializedName(JsonKeyNames.MEDIA_TYPE)
    val mediaType: String,
    @SerializedName(JsonKeyNames.MEDIA_ID)
    val mediaId: Int,
    val favorite: Boolean
)