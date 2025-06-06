package com.irfangujjar.tmdb.features.main.search.domain.repos

import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchRepo {
    suspend fun loadTrendingSearch()
    fun getTrendingSearchFlow(): Flow<SearchModel?>
    suspend fun loadSearchSuggestions(query: String): SearchModel
    suspend fun loadSearchDetails(query: String): SearchDetailsModel
}