package com.irfangujjar.tmdb.core.di.account_details

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.local.AccountDetailsLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.AccountDetailsRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.tmdb.data.data_sources.remote.api.AccountDetailsApi
import com.irfangujjar.tmdb.features.main.tmdb.data.repos.AccountDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.tmdb.domain.repos.AccountDetailsRepo
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseLoadWithoutId
import com.irfangujjar.tmdb.features.main.tmdb.domain.usecase.AccountDetailsUseCaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class AccountDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(
        retrofit: Retrofit,
        dataStore: DataStore<Preferences>
    ): AccountDetailsRepo =
        AccountDetailsRepoImpl(
            remoteDataSource = AccountDetailsRemoteDataSourceImpl(
                api = retrofit.create(AccountDetailsApi::class.java)
            ),
            localDataSource = AccountDetailsLocalDataSourceImpl(
                dataStore = dataStore
            )
        )


    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(
        repo: AccountDetailsRepo
    ): AccountDetailsUseCaseLoad =
        AccountDetailsUseCaseLoad(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadWithoutId(
        repo: AccountDetailsRepo
    ): AccountDetailsUseCaseLoadWithoutId =
        AccountDetailsUseCaseLoadWithoutId(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatch(
        repo: AccountDetailsRepo
    ): AccountDetailsUseCaseWatch =
        AccountDetailsUseCaseWatch(
            repo = repo
        )

}