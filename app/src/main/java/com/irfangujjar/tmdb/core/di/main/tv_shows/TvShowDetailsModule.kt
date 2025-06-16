package com.irfangujjar.tmdb.core.di.main.tv_shows

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources.TvShowDetailsDataSourceImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.data_sources.api.TvShowDetailsApi
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.data.repos.TvShowDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.usecases.TvShowDetailsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class TvShowDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesTvShowDetailsUseCaseLoad(retrofit: Retrofit): TvShowDetailsUseCaseLoad =
        TvShowDetailsUseCaseLoad(
            repo = TvShowDetailsRepoImpl(
                dataSource = TvShowDetailsDataSourceImpl(
                    api = retrofit.create<TvShowDetailsApi>(TvShowDetailsApi::class.java)
                )
            )
        )
}