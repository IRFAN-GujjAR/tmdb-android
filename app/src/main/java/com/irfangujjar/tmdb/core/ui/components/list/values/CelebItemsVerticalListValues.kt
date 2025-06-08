package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel

data class CelebItemsVerticalListValues(
    val celebsIds: List<Int>,
    val celebsNames: List<String>,
    val celebsKnownFor: List<String?>,
    val profilePaths: List<String?>
) {
    companion object {
        fun dummyData(): CelebItemsVerticalListValues = fromCelebs(
            celebs =
                List(20) { CelebModel.dummyData() })

        fun fromCelebs(celebs: List<CelebModel>): CelebItemsVerticalListValues {
            val celebsIds = mutableListOf<Int>()
            val celebsNames = mutableListOf<String>()
            val celebsKnownFor = mutableListOf<String?>()
            val profilePaths = mutableListOf<String?>()

            celebs.forEach {
                celebsIds.add(it.id)
                celebsNames.add(it.name)
                celebsKnownFor.add(it.knownFor)
                profilePaths.add(it.profilePath)
            }
            return CelebItemsVerticalListValues(
                celebsIds = celebsIds,
                celebsNames = celebsNames,
                celebsKnownFor = celebsKnownFor,
                profilePaths = profilePaths
            )
        }
    }
}

