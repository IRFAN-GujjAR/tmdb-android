package com.irfangujjar.tmdb.core.di.login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.login.data.data_source.local.LoginLocalDataSourceImpl
import com.irfangujjar.tmdb.features.login.data.data_source.remote.LoginRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.login.data.data_source.remote.api.LoginApi
import com.irfangujjar.tmdb.features.login.data.repository.LoginRepositoryImpl
import com.irfangujjar.tmdb.features.login.domain.usecase.LoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class LoginModule {
    @Provides
    @ViewModelScoped
    fun providesLoginRepo(retrofit: Retrofit, dataSource: DataStore<Preferences>): LoginUseCase =
        LoginUseCase(
            repo = LoginRepositoryImpl(
                remoteDataSource = LoginRemoteDataSourceImpl(
                    api = retrofit.create(LoginApi::class.java)
                ),
                localDataSource = LoginLocalDataSourceImpl(
                    dataSource = dataSource
                )
            )
        )
}