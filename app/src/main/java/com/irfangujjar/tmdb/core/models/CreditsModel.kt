package com.irfangujjar.tmdb.core.models

import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie

data class CreditsModel(
    val cast: List<CastModel>,
    val crew: List<CrewModel>
) {
    companion object {
        fun dummyData(type: MediaType): CreditsModel = CreditsModel(
            cast = List(20) { CastModel.dummyData(type = type) },
            crew = if (type.isMovie()) List(20) { CrewModel.dummyData() } else emptyList()
        )
    }
}
