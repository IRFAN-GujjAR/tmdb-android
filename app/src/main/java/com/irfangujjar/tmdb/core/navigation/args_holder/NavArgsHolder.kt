package com.irfangujjar.tmdb.core.navigation.args_holder

import java.util.UUID

data class NavArg<T>(
    val uuid: UUID,
    val data: T
)

open class NavArgsHolder<T> {
    private val items = mutableListOf<NavArg<T>>()

    fun saveArgData(data: T): String {
        val arg = NavArg(
            uuid = UUID.randomUUID(),
            data = data
        )
        items.add(arg)
        return arg.uuid.toString()
    }

    fun getArgData(argsId: String): T? = items.find { it.uuid == UUID.fromString(argsId) }?.data

    fun removeArg(argsId: String) = items.removeIf { it.uuid == UUID.fromString(argsId) }
}