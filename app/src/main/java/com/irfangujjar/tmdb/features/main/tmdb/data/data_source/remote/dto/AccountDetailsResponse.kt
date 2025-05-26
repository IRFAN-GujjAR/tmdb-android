package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsEntity

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
        id = id,
        username = username,
        avatarPath = avatar.tmdb.avatarPath
    )
}