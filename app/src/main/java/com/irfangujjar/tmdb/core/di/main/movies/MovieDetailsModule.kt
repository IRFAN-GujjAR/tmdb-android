package com.irfangujjar.tmdb.core.di.main.movies

import com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.data_sources.MovieDetailsDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.data_sources.api.MovieDetailsApi
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.data.repos.MovieDetailsRepoImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.MovieDetailsUseCaseLoad
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
class MovieDetailsModule {

    @Provides
    @ViewModelScoped
    fun providesMovieDetailsUseCaseLoad(retrofit: Retrofit): MovieDetailsUseCaseLoad =
        MovieDetailsUseCaseLoad(
            repo = MovieDetailsRepoImpl(
                dataSource = MovieDetailsDataSourceImpl(
                    api = retrofit.create<MovieDetailsApi>(MovieDetailsApi::class.java)
                )
            )
        )
}