package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie


data class VideosModel(
    @SerializedName(JsonKeyNames.RESULTS)
    val videos: List<VideoModel>
) {
    companion object {
        fun dummyData(type: MediaType): VideosModel = VideosModel(
            videos = List(20) { VideoModel.dummyData(type = type) }
        )
    }
}

data class VideoModel(
    val key: String,
    val name: String
) {
    companion object {
        fun dummyData(type: MediaType): VideoModel = if (type.isMovie()) VideoModel(
            key = "tlFyyzXVEMk",
            name = "Robert De Niro Auditioning for Sonny Corleone in The Godfather"
        ) else VideoModel(
            key = "EM12mcTEI88",
            name = "Series Launch Trailer"
        )
    }
}
