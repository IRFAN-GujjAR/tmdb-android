package com.irfangujjar.tmdb.features.signout.data.repos

import android.util.Log
import com.irfangujjar.tmdb.features.signout.data.data_sources.local.SignOutLocalDataSource
import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.SignOutRemoteDataSource
import com.irfangujjar.tmdb.features.signout.domain.repos.SignOutRepo
import retrofit2.HttpException

class SignOutRepoImpl(
    private val localDS: SignOutLocalDataSource,
    private val remoteDS: SignOutRemoteDataSource
) : SignOutRepo {
    override suspend fun signOut(sessionId: String) {
        try {
            val result = remoteDS.signOut(sessionId)
            Log.d("SignOut", "Result : ${result.success}")
            localDS.deleteUserDetails()
        } catch (e: HttpException) {
            if (e.code() == 404) {
                localDS.deleteUserDetails()
            } else {
                throw e
            }
        }
    }
}