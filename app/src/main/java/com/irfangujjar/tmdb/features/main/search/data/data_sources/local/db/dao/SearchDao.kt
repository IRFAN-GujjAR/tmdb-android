package com.irfangujjar.tmdb.features.main.search.data.data_sources.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingSearch(trendingSearch: SearchModel)

    @Query("SELECT * FROM trending_search_table WHERE id=0")
    fun getTrendingSearchFlow(): Flow<SearchModel?>
}