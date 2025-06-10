package com.irfangujjar.tmdb.core.di.main.movies

import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.local.MoviesLocalDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.MoviesRemoteDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.movies.data.repos.MoviesRepoImpl
import com.irfangujjar.tmdb.features.main.movies.domain.repos.MoviesRepo
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseWatch
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.data_sources.SeeAllMoviesDataSourceImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.data.repos.SeeAllMoviesRepoImpl
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.SeeAllMoviesUseCaseLoad
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
    ): MoviesRepo =
        MoviesRepoImpl(
            localDS = MoviesLocalDataSourceImpl(dao = db.moviesDao()),
            remoteDS = MoviesRemoteDataSourceImpl(api = movieApi)
        )

    @Provides
    @ViewModelScoped
    fun providesUseCaseWatch(repo: MoviesRepo): MoviesUseCaseWatch =
        MoviesUseCaseWatch(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesUseCaseLoad(repo: MoviesRepo): MoviesUseCaseLoad =
        MoviesUseCaseLoad(repo = repo)

    @Provides
    @ViewModelScoped
    fun providesSeeAllMoviesUseCaseLoad(api: MovieApi): SeeAllMoviesUseCaseLoad =
        SeeAllMoviesUseCaseLoad(
            repo = SeeAllMoviesRepoImpl(
                dataSource = SeeAllMoviesDataSourceImpl(
                    api = api
                )
            )
        )
}