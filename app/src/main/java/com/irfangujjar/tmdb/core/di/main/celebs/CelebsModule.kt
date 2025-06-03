package com.irfangujjar.tmdb.core.di.main.celebs

import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.local.CelebsLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.CelebsRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api.CelebApi
import com.irfangujjar.tmdb.features.main.celebs.data.repos.CelebsRepoImpl
import com.irfangujjar.tmdb.features.main.celebs.domain.repos.CelebsRepo
import com.irfangujjar.tmdb.features.main.celebs.domain.usecases.CelebsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.domain.usecases.CelebsUseCaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class CelebsModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(db: AppDatabase, api: CelebApi): CelebsRepo =
        CelebsRepoImpl(
            localDS = CelebsLocalDataSourceImpl(dao = db.celebsDao()),
            remoteDS = CelebsRemoteDataSourceImpl(api = api)
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatch(repo: CelebsRepo): CelebsUseCaseWatch = CelebsUseCaseWatch(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(repo: CelebsRepo): CelebsUseCaseLoad = CelebsUseCaseLoad(repo = repo)
}