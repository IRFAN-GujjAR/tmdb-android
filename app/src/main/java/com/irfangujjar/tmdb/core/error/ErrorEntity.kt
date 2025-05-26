package com.irfangujjar.tmdb.core.error

data class ErrorEntity(
    val type: ErrorType,
    val message: String
)
