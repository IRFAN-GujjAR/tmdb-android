package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName(JsonKeyNames.RESULTS) val searches: List<SearchItemResponse>
)