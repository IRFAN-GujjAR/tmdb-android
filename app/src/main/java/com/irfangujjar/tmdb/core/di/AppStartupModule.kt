package com.irfangujjar.tmdb.core.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.app_startup.data.data_source.local.AppStartupLocalDataSourceImpl
import com.irfangujjar.tmdb.features.app_startup.data.repositories.AppStartupRepositoryImpl
import com.irfangujjar.tmdb.features.app_startup.domain.repositories.AppStartupRepository
import com.irfangujjar.tmdb.features.app_startup.domain.usecase.AppStartupUseCaseLoadData
import com.irfangujjar.tmdb.features.app_startup.domain.usecase.AppStartupUseCaseSave
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
    fun providesRepo(dataStore: DataStore<Preferences>): AppStartupRepository =
        AppStartupRepositoryImpl(
            localDS = AppStartupLocalDataSourceImpl(
                dataStore = dataStore
            )
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadData(repo: AppStartupRepository): AppStartupUseCaseLoadData =
        AppStartupUseCaseLoadData(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseSave(repo: AppStartupRepository): AppStartupUseCaseSave =
        AppStartupUseCaseSave(
            repo = repo
        )

}