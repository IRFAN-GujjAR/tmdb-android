package com.irfangujjar.tmdb.features.main.search.data.repos

import com.irfangujjar.tmdb.features.main.search.data.data_sources.local.SearchLocalDataSource
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.SearchRemoteDataSource
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchItemModel
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import kotlinx.coroutines.flow.Flow

class SearchRepoImpl(
    private val localDS: SearchLocalDataSource,
    private val remoteDS: SearchRemoteDataSource
) : SearchRepo {
    override suspend fun loadTrendingSearch() {
        val trendingSearch = remoteDS.loadTrendingSearch()
        val trendingSearchModel = SearchModel(
            searches = trendingSearch.searches.map { SearchItemModel(searchTitle = it.getSearchTitle()) }
        )
        localDS.insertTrendingSearch(trendingSearchModel)
    }

    override fun getTrendingSearchFlow(): Flow<SearchModel?> = localDS.getTrendingSearchFlow()
}