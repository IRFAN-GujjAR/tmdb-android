package com.irfangujjar.tmdb.features.main.search.data.data_sources.remote

import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api.SearchApi
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto.SearchResponse

interface SearchRemoteDataSource {
    suspend fun loadTrendingSearch(): SearchResponse
}

class SearchRemoteDataSourceImpl(
    private val api: SearchApi
) : SearchRemoteDataSource {
    override suspend fun loadTrendingSearch(): SearchResponse = api.trendingSearch()
}