package com.irfangujjar.tmdb.core.di.main.tv_shows

import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources.SeasonDetailsDataSourceImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.data_sources.api.SeasonDetailsApi
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.data.repos.SeasonDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.usecases.SeasonDetailsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class SeasonDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesSeasonDetailsUseCaseLoad(retrofit: Retrofit): SeasonDetailsUseCaseLoad =
        SeasonDetailsUseCaseLoad(
            repo = SeasonDetailsRepoImpl(
                dataSource = SeasonDetailsDataSourceImpl(
                    api = retrofit.create<SeasonDetailsApi>(SeasonDetailsApi::class.java)
                )
            )
        )
}