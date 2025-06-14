package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie

data class BackdropImageModel(
    @SerializedName(JsonKeyNames.FILE_PATH)
    val filePath: String
) {
    companion object {
        fun dummyData(type: MediaType): BackdropImageModel = BackdropImageModel(
            filePath = if (type.isMovie()) "/AbgEQO2mneCSOc8CSnOMa8pBS8I.jpg" else
                "/or7wKwv1IT6LEOktt395O5qi7e.jpg"
        )
    }
}

data class BackdropImagesModel(
    @SerializedName(JsonKeyNames.BACKDROPS)
    val backdrops: List<BackdropImageModel>
) {
    companion object {
        fun dummyData(type: MediaType): BackdropImagesModel = BackdropImagesModel(
            backdrops = List(20) { BackdropImageModel.dummyData(type = type) }
        )
    }
}