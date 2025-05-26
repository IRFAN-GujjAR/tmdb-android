package com.irfangujjar.tmdb.core.error

sealed class ErrorType {
    object Network : ErrorType()
    object Server : ErrorType()
    data class Unknown(val e: Exception) : ErrorType()
}