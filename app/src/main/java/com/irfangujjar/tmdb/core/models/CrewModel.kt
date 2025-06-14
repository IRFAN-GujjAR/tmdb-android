package com.irfangujjar.tmdb.core.models

import JsonKeyNames
import com.google.gson.annotations.SerializedName

data class CrewModel(
    val id: Int,
    val name: String,
    val job: String?,
    val department: String,
    @SerializedName(JsonKeyNames.PROFILE_PATH)
    val profilePath: String?
) {
    companion object {
        fun dummyData(): CrewModel = CrewModel(
            id = 457,
            name = "Albert S. Ruddy",
            job = "Producer",
            department = "Production",
            profilePath = "/jSkLkR79OmqYHg0kx4WWXI1TYPP.jpg"
        )
    }
}
