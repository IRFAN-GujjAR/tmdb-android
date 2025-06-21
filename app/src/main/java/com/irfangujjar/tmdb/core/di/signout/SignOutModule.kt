package com.irfangujjar.tmdb.core.di.signout

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.signout.data.data_sources.local.SignOutLocalDataSourceImpl
import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.SignOutRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.signout.data.data_sources.remote.api.SignOutApi
import com.irfangujjar.tmdb.features.signout.data.repos.SignOutRepoImpl
import com.irfangujjar.tmdb.features.signout.domain.usecases.SignOutUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SignOutModule {
    
    @Provides
    @ViewModelScoped
    fun signOutUseCase(retrofit: Retrofit, dataStore: DataStore<Preferences>): SignOutUseCase =
        SignOutUseCase(
            repo = SignOutRepoImpl(
                localDS = SignOutLocalDataSourceImpl(dataStore = dataStore),
                remoteDS = SignOutRemoteDataSourceImpl(api = retrofit.create<SignOutApi>(SignOutApi::class.java))
            )
        )
}