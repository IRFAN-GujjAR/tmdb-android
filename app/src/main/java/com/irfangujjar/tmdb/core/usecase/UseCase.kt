package com.irfangujjar.tmdb.core.usecase


interface UseCase<out T, in Params> {
    suspend fun invoke(params: Params): T
}

interface UseCaseWithoutParams<out T> {
    suspend fun invoke(): T
}

interface UseCaseWithoutReturnType<in Params> {
    suspend fun invoke(params: Params)
}

interface UseCaseWithoutParamsAndReturnType {
    suspend fun invoke()
}

interface UseCaseWithoutAsync<out T, in Params> {
    fun invoke(params: Params): T
}

interface UseCaseWithoutAsyncAndParams<out T> {
    fun invoke(): T
}

object NoParams