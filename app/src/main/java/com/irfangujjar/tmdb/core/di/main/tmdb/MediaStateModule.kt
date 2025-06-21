package com.irfangujjar.tmdb.core.di.main.tmdb

import com.irfangujjar.tmdb.features.media_state.data.data_sources.MediaStateDataSourceImpl
import com.irfangujjar.tmdb.features.media_state.data.data_sources.api.MediaStateApi
import com.irfangujjar.tmdb.features.media_state.data.repos.MediaStateRepoImpl
import com.irfangujjar.tmdb.features.media_state.domain.usecases.MediaStateUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MediaStateModule {

    @Provides
    @ViewModelScoped
    fun providesUseCase(retrofit: Retrofit): MediaStateUseCaseLoad =
        MediaStateUseCaseLoad(
            repo = MediaStateRepoImpl(
                dataSource = MediaStateDataSourceImpl(
                    api = retrofit.create<MediaStateApi>(MediaStateApi::class.java)
                )
            )
        )
}