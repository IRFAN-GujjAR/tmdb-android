package com.irfangujjar.tmdb.features.signout.domain.repos

interface SignOutRepo {
    suspend fun signOut(sessionId: String)
}