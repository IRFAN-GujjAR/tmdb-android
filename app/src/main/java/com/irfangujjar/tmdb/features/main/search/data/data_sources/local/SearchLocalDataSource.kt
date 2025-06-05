package com.irfangujjar.tmdb.features.main.search.data.data_sources.local

import com.irfangujjar.tmdb.features.main.search.data.data_sources.local.db.dao.SearchDao
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import kotlinx.coroutines.flow.Flow

interface SearchLocalDataSource {
    suspend fun insertTrendingSearch(search: SearchModel)
    fun getTrendingSearchFlow(): Flow<SearchModel?>
}

class SearchLocalDataSourceImpl(
    private val dao: SearchDao
) : SearchLocalDataSource {
    override suspend fun insertTrendingSearch(search: SearchModel) =
        dao.insertTrendingSearch(search)

    override fun getTrendingSearchFlow(): Flow<SearchModel?> = dao.getTrendingSearchFlow()

}