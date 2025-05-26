package com.irfangujjar.tmdb.core.api

import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.core.error.ErrorType
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T) : ResultWrapper<T>()
    data class Error(val errorEntity: ErrorEntity) : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return try {
        val response = apiCall()
        ResultWrapper.Success(response)
    } catch (e: Exception) {
        when (e) {
            is TimeoutException, is SocketTimeoutException,
            is UnknownHostException -> {
                ResultWrapper.Error(
                    ErrorEntity(
                        message = "Request timed out. Please try again.",
                        type = ErrorType.Network
                    )
                )
            }

            else -> {
                ResultWrapper.Error(
                    ErrorEntity(
                        message = "Unknown error occurred",
                        type = ErrorType.Unknown(e)
                    )
                )
            }
        }

    }
}