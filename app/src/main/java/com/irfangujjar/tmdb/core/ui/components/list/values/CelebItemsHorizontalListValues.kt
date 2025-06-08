package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel

data class CelebItemsHorizontalListValues(
    val celebIds: List<Int>,
    val celebsNames: List<String>,
    val celebsKnownFor: List<String?>,
    val profilePaths: List<String?>,
    val config: CelebItemHorizontalConfigValues
) {
    companion object {

        fun dummyData(): CelebItemsHorizontalListValues {
            val celebs = List(20) { CelebModel.dummyData() }
            return CelebItemsHorizontalListValues(
                celebIds = celebs.map { it.id },
                celebsNames = celebs.map { it.name },
                celebsKnownFor = celebs.map { it.knownFor },
                profilePaths = celebs.map { it.profilePath },
                config = CelebItemHorizontalConfigValues.fromDefault()
            )
        }

        fun fromListValues(
            celebs: List<CelebModel>,
            config: CelebItemHorizontalConfigValues
        ): CelebItemsHorizontalListValues {

            val celebIds = mutableListOf<Int>()
            val celebsNames = mutableListOf<String>()
            val celebsKnownFor = mutableListOf<String?>()
            val profilePaths = mutableListOf<String?>()

            celebs.forEach {
                celebIds.add(it.id)
                celebsNames.add(it.name)
                celebsKnownFor.add(it.knownFor)
                profilePaths.add(it.profilePath)
            }

            return CelebItemsHorizontalListValues(
                celebIds = celebIds,
                celebsNames = celebsNames,
                celebsKnownFor = celebsKnownFor,
                profilePaths = profilePaths,
                config = config
            )
        }
    }
}
