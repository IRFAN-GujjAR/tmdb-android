package com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CelebsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCelebrities(celebrities: CelebsModel)

    @Query("SELECT * FROM celebs_table WHERE id=0")
    fun getCelebritiesFlow(): Flow<CelebsModel?>
}