package com.irfangujjar.tmdb.core.di.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.irfangujjar.tmdb.features.theme.data.data_sources.local.UserThemeLocalDataSourceImpl
import com.irfangujjar.tmdb.features.theme.data.repository.UserThemeRepositoryImpl
import com.irfangujjar.tmdb.features.theme.domain.repository.UserThemeRepository
import com.irfangujjar.tmdb.features.theme.domain.usecase.UserThemeUseCaseSave
import com.irfangujjar.tmdb.features.theme.domain.usecase.UserThemeUseCaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UserThemeModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(dataStore: DataStore<Preferences>): UserThemeRepository {
        return UserThemeRepositoryImpl(
            localDS = UserThemeLocalDataSourceImpl(
                dataStore = dataStore
            )
        )
    }

    @Provides
    @ViewModelScoped
    fun providesSaveUseCase(repo: UserThemeRepository): UserThemeUseCaseSave {
        return UserThemeUseCaseSave(
            repo = repo
        )
    }

    @Provides
    @ViewModelScoped
    fun providesWatchUseCase(repo: UserThemeRepository): UserThemeUseCaseWatch {
        return UserThemeUseCaseWatch(
            repo = repo
        )
    }
}