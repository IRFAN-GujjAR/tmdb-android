package com.irfangujjar.tmdb.features.main.celebs.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celebs_table")
data class CelebsModel(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val popular: CelebsListModel,
    val trending: CelebsListModel
)
