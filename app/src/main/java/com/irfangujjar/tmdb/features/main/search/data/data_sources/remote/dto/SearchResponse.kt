package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchItemModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel

data class SearchResponse(
    @SerializedName(JsonKeyNames.RESULTS) val searches: List<SearchItemResponse>
)

fun SearchResponse.toModel(): SearchModel = SearchModel(
    searches = searches.map { SearchItemModel(searchTitle = it.getSearchTitle()) }
)
