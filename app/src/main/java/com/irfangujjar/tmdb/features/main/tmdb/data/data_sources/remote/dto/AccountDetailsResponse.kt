package com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.dto

import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity

data class AccountDetailsResponse(
    val id: Int,
    val username: String,
    val avatar: AvatarModel
)

data class AvatarModel(
    val tmdb: AvatarPathModel
)

data class AvatarPathModel(
    @SerializedName("avatar_path") val avatarPath: String?
)


fun AccountDetailsResponse.toEntity(): AccountDetailsEntity {
    return AccountDetailsEntity(
        userId = id,
        username = username,
        profilePath = avatar.tmdb.avatarPath
    )
}

fun AccountDetailsResponse.toEntityWithoutId(): AccountDetailsWithoutIdEntity {
    return AccountDetailsWithoutIdEntity(
        username = username,
        profilePath = avatar.tmdb.avatarPath
    )
}