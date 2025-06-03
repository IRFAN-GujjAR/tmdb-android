package com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(tvShows: TvShowsModel)

    @Query("SELECT * FROM tv_shows_table WHERE id=0")
    fun getTvShowsFlow(): Flow<TvShowsModel?>
}