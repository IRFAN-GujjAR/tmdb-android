package com.irfangujjar.tmdb.core.di.main.movies

import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.MoviesLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.MoviesRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.movies.data.repositories.MoviesRepositoryImpl
import com.irfangujjar.tmdb.features.main.movies.domain.repositories.MoviesRepository
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseWatch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MoviesModule {

    @Provides
    @ViewModelScoped
    fun providesRepo(
        db: AppDatabase,
        movieApi: MovieApi
    ): MoviesRepository =
        MoviesRepositoryImpl(
            localDS = MoviesLocalDataSourceImpl(dao = db.moviesDao()),
            remoteDS = MoviesRemoteDataSourceImpl(api = movieApi)
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatch(repo: MoviesRepository): MoviesUseCaseWatch =
        MoviesUseCaseWatch(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(repo: MoviesRepository): MoviesUseCaseLoad =
        MoviesUseCaseLoad(repo = repo)
}