package com.irfangujjar.tmdb.core.di.main.search

import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.features.main.search.data.data_sources.local.SearchLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.SearchRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api.SearchApi
import com.irfangujjar.tmdb.features.main.search.data.repos.SearchRepoImpl
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseLoad
import com.irfangujjar.tmdb.features.main.search.domain.usecases.TrendingSearchUseCaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SearchModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(db: AppDatabase, api: SearchApi): SearchRepo =
        SearchRepoImpl(
            localDS = SearchLocalDataSourceImpl(dao = db.searchDao()),
            remoteDS = SearchRemoteDataSourceImpl(api = api)
        )

    @Provides
    @ViewModelScoped
    fun providesTrendingSearchUseCaseLoad(repo: SearchRepo): TrendingSearchUseCaseLoad =
        TrendingSearchUseCaseLoad(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesTrendingSearchUseCaseWatch(repo: SearchRepo): TrendingSearchUseCaseWatch =
        TrendingSearchUseCaseWatch(repo = repo)
}