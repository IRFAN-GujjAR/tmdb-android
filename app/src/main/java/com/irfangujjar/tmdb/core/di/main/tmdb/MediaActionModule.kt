package com.irfangujjar.tmdb.core.di.main.tmdb

import com.irfangujjar.tmdb.features.media_action.data.data_sources.MediaActionDataSourceImpl
import com.irfangujjar.tmdb.features.media_action.data.data_sources.api.MediaActionApi
import com.irfangujjar.tmdb.features.media_action.data.repos.MediaActionRepoImpl
import com.irfangujjar.tmdb.features.media_action.domain.repos.MediaActionRepo
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseFavorite
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseRate
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseUnRate
import com.irfangujjar.tmdb.features.media_action.domain.usecases.MediaActionUseCaseWatchlist
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MediaActionModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(retrofit: Retrofit): MediaActionRepo =
        MediaActionRepoImpl(
            dataSource = MediaActionDataSourceImpl(
                api = retrofit.create<MediaActionApi>(MediaActionApi::class.java)
            )
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseFavorite(repo: MediaActionRepo): MediaActionUseCaseFavorite =
        MediaActionUseCaseFavorite(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseRate(repo: MediaActionRepo): MediaActionUseCaseRate =
        MediaActionUseCaseRate(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseUnRate(repo: MediaActionRepo): MediaActionUseCaseUnRate =
        MediaActionUseCaseUnRate(
            repo = repo
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatchlist(repo: MediaActionRepo): MediaActionUseCaseWatchlist =
        MediaActionUseCaseWatchlist(
            repo = repo
        )
}