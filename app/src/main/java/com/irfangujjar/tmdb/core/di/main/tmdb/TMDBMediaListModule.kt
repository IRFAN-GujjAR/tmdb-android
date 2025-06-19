package com.irfangujjar.tmdb.core.di.main.tmdb

import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.TMDBMediaListDataSourceImpl
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListApi
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.repos.TMDBMediaListRepoImpl
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.repos.TMDBMediaListRepo
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadMovies
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadMoviesAndTvShows
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadTvShows
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit


@Module
@InstallIn(ViewModelComponent::class)
class TMDBMediaListModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(retrofit: Retrofit): TMDBMediaListRepo = TMDBMediaListRepoImpl(
        dataSource = TMDBMediaListDataSourceImpl(
            api = retrofit.create<TMDBMediaListApi>(TMDBMediaListApi::class.java)
        )
    )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadMoviesAndTvShows(repo: TMDBMediaListRepo):
            TMDBMediaListUseCaseLoadMoviesAndTvShows = TMDBMediaListUseCaseLoadMoviesAndTvShows(
        repo = repo
    )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadMovies(repo: TMDBMediaListRepo):
            TMDBMediaListUseCaseLoadMovies = TMDBMediaListUseCaseLoadMovies(
        repo = repo
    )

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoadTvShows(repo: TMDBMediaListRepo):
            TMDBMediaListUseCaseLoadTvShows = TMDBMediaListUseCaseLoadTvShows(
        repo = repo
    )

}