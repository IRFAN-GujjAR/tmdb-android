package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel

data class CelebItemVerticalValues(
    val id: Int,
    val name: String,
    val knownFor: String?,
    val profilePath: String?,
) {
    companion object {
        fun dummyData(): CelebItemVerticalValues {
            val celeb = CelebModel.dummyData()
            return CelebItemVerticalValues(
                id = celeb.id,
                name = celeb.name,
                knownFor = celeb.knownFor,
                profilePath = celeb.profilePath
            )
        }

        fun fromListValues(
            values: CelebItemsVerticalListValues,
            index: Int
        ): CelebItemVerticalValues =
            CelebItemVerticalValues(
                id = values.celebsIds[index],
                name = values.celebsNames[index],
                knownFor = values.celebsKnownFor[index],
                profilePath = values.profilePaths[index]
            )
    }
}
