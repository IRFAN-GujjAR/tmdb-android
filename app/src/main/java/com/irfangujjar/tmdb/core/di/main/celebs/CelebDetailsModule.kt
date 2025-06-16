package com.irfangujjar.tmdb.core.di.main.celebs

import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources.CelebDetailsDataSourceImpl
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.data_sources.api.CelebDetailsApi
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.data.repos.CelebDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.usecases.CelebDetailsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CelebDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesCelebDetailsUseCaseLoad(retrofit: Retrofit): CelebDetailsUseCaseLoad =
        CelebDetailsUseCaseLoad(
            repo = CelebDetailsRepoImpl(
                dataSource = CelebDetailsDataSourceImpl(
                    api = retrofit.create<CelebDetailsApi>(CelebDetailsApi::class.java)
                )
            )
        )
}