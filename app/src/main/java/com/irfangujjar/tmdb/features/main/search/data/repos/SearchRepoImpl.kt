package com.irfangujjar.tmdb.features.main.search.data.repos

import com.irfangujjar.tmdb.features.main.search.data.data_sources.local.SearchLocalDataSource
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.SearchRemoteDataSource
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.dto.toModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import kotlinx.coroutines.flow.Flow

class SearchRepoImpl(
    private val localDS: SearchLocalDataSource,
    private val remoteDS: SearchRemoteDataSource
) : SearchRepo {
    override suspend fun loadTrendingSearch() {
        val trendingSearch = remoteDS.loadTrendingSearch()
        localDS.insertTrendingSearch(trendingSearch.toModel())
    }

    override fun getTrendingSearchFlow(): Flow<SearchModel?> = localDS.getTrendingSearchFlow()

    override suspend fun loadSearchSuggestions(query: String): SearchModel =
        remoteDS.loadSearchSuggestions(query).toModel()

    override suspend fun loadSearchDetails(query: String): SearchDetailsModel =
        remoteDS.loadSearchDetails(query = query)
}