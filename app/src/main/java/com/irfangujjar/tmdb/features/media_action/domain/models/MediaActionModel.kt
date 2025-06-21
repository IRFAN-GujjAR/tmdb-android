package com.irfangujjar.tmdb.features.media_action.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class MediaActionModel(
    @SerializedName(JsonKeyNames.STATUS_CODE)
    val statusCode: Int?,
    @SerializedName(JsonKeyNames.STATUS_MESSAGE)
    val statusMessage: String?
)

//final int? statusCode;
//@JsonKey(name: JsonKeysNames.statusMessage)
//final String? statusMessage;
