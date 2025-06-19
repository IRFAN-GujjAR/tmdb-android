package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models

import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class TvShowCreditsModel(
    val cast: List<TvShowModel>,
    val crew: List<TvShowModel>
) {
    fun isBothNotEmpty(): Boolean = isCastNotEmpty() && isCrewNotEmpty()

    fun isCastNotEmpty(): Boolean = !cast.isEmpty()
    fun isCrewNotEmpty(): Boolean = !crew.isEmpty()

    companion object {
        fun dummyDate(): TvShowCreditsModel =
            TvShowCreditsModel(
                cast = List(20) { TvShowModel.dummyData() },
                crew = List(20) { TvShowModel.dummyData() }
            )
    }
}
