package com.irfangujjar.tmdb.core.di

import com.google.gson.Gson

object GsonProvider {
    val gson by lazy { Gson() }
}