package com.irfangujjar.tmdb.core.di.main.tv_shows

import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.local.TvShowsLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.TvShowsRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api.TvShowApi
import com.irfangujjar.tmdb.features.main.tv_shows.data.repos.TvShowsRepoImpl
import com.irfangujjar.tmdb.features.main.tv_shows.domain.repos.TvShowsRepo
import com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases.TvShowsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.domain.usecases.TvShowsUseCaseWatch
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.data_sources.SeeAllTvShowsDataSourceImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.data.repos.SeeAllTvShowsRepoImpl
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.SeeAllTvShowsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class TvShowsModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(db: AppDatabase, api: TvShowApi): TvShowsRepo = TvShowsRepoImpl(
        localDS = TvShowsLocalDataSourceImpl(dao = db.tvShowsDao()),
        remoteDS = TvShowsRemoteDataSourceImpl(api = api)
    )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatch(repo: TvShowsRepo): TvShowsUseCaseWatch =
        TvShowsUseCaseWatch(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(repo: TvShowsRepo): TvShowsUseCaseLoad =
        TvShowsUseCaseLoad(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesSeeAllTvShowsUseCaseLoad(api: TvShowApi): SeeAllTvShowsUseCaseLoad =
        SeeAllTvShowsUseCaseLoad(
            repo = SeeAllTvShowsRepoImpl(
                dataSource = SeeAllTvShowsDataSourceImpl(
                    api = api
                )
            )
        )

}