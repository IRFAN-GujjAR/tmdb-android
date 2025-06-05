package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class SearchItemResponse(
    @SerializedName(JsonKeyNames.MEDIA_TYPE) val mediaType: String,
    val title: String?,
    val name: String?
) {
    fun getSearchTitle(): String =
        if (mediaType == "movie")
            title!!
        else
            name!!

}
