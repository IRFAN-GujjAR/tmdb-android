package com.irfangujjar.tmdb.features.main.celebs.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celebs_table")
data class CelebsModel(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val popular: CelebsListModel,
    val trending: CelebsListModel
) {
    companion object {
        fun dummyData(): CelebsModel {
            val celebList = CelebsListModel(
                pageNo = 1,
                totalPages = 2,
                celebrities = List(20) { CelebModel.dummyData() }
            )
            return CelebsModel(
                popular = celebList,
                trending = celebList
            )
        }
    }
}
