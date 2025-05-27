package com.irfangujjar.tmdb.features.main.tmdb.data.data_source.local.dto

import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity


data class AccountDetailsWithoutIdDSModel(
    val username: String,
    val profilePath: String?
)

fun AccountDetailsWithoutIdDSModel.toEntityWithoutId(): AccountDetailsWithoutIdEntity {
    return AccountDetailsWithoutIdEntity(
        username = username,
        profilePath = profilePath
    )
}