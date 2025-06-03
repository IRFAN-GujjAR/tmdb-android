package com.irfangujjar.tmdb.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.app_startup.data.data_sources.local.AppStartupLocalDataSourceImpl
import com.irfangujjar.tmdb.features.app_startup.data.repos.AppStartupRepoImpl
import com.irfangujjar.tmdb.features.app_startup.domain.repos.AppStartupRepo
import com.irfangujjar.tmdb.features.app_startup.domain.usecases.AppStartupUseCaseLoadData
import com.irfangujjar.tmdb.features.app_startup.domain.usecases.AppStartupUseCaseSave
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AppStartupModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(dataStore: DataStore<Preferences>): AppStartupRepo =
        AppStartupRepoImpl(
            localDS = AppStartupLocalDataSourceImpl(
                dataStore = dataStore
            )
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadData(repo: AppStartupRepo): AppStartupUseCaseLoadData =
        AppStartupUseCaseLoadData(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseSave(repo: AppStartupRepo): AppStartupUseCaseSave =
        AppStartupUseCaseSave(
            repo = repo
        )

}