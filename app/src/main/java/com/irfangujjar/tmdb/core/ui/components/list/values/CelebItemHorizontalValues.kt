package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel

data class CelebItemHorizontalValues(
    val id: Int,
    val name: String,
    val knownFor: String?,
    val profilePath: String?,
    val config: CelebItemHorizontalConfigValues
) {
    companion object {
        fun dummyData(): CelebItemHorizontalValues {
            val celeb = CelebModel.dummyData()
            return CelebItemHorizontalValues(
                id = celeb.id,
                name = celeb.name,
                knownFor = celeb.knownFor,
                profilePath = celeb.profilePath,
                config = CelebItemHorizontalConfigValues.fromDefault()
            )
        }

        fun fromListValues(
            values: CelebItemsHorizontalValues,
            index: Int
        ): CelebItemHorizontalValues {
            return CelebItemHorizontalValues(
                id = values.celebIds[index],
                name = values.celebsNames[index],
                knownFor = values.celebsKnownFor[index],
                profilePath = values.profilePaths[index],
                config = values.config
            )
        }
    }
}
