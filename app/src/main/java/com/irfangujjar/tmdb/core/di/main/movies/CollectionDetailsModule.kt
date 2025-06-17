package com.irfangujjar.tmdb.core.di.main.movies

import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources.CollectionDetailsDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.data_sources.api.CollectionDetailsApi
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.data.repos.CollectionDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.usecases.CollectionDetailsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class CollectionDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(retrofit: Retrofit): CollectionDetailsUseCaseLoad =
        CollectionDetailsUseCaseLoad(
            repo = CollectionDetailsRepoImpl(
                dataSource = CollectionDetailsDataSourceImpl(
                    api = retrofit.create<CollectionDetailsApi>(
                        CollectionDetailsApi::class.java
                    )
                )
            )
        )
}