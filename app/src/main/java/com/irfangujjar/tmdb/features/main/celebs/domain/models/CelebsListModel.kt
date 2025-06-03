package com.irfangujjar.tmdb.features.main.celebs.domain.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class CelebsListModel(
    @SerializedName(JsonKeyNames.PAGE_NO) val pageNo: Int,
    @SerializedName(JsonKeyNames.TOTAL_PAGES) val totalPages: Int,
    @SerializedName(JsonKeyNames.RESULTS) val celebrities: List<CelebModel>
)