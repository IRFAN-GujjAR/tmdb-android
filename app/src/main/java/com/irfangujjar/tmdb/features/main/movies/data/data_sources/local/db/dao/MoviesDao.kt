package com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: MoviesModel)

    @Query("SELECT * FROM movies_table WHERE id=0")
    fun getMoviesFlow(): Flow<MoviesModel?>
}