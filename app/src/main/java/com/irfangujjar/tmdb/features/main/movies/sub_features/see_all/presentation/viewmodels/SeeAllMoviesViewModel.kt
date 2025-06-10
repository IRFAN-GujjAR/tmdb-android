package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.SeeAllMoviesUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.params.SeeAllMoviesUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder.SeeAllMoviesNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SeeAllMoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllMoviesNavArgsHolder,
    private val useCaseLoad: SeeAllMoviesUseCaseLoad
) : PaginatedViewModel<MoviesListModel, SeeAllMoviesUseCaseLoadParams>(
    initialState = getInitialState<MoviesListModel, HomeScreen.SeeAllMovies>(
        savedStateHandle = savedStateHandle,
        navArgsHolder = navArgsHolder,
    )!!
) {

    val args = getArgs<HomeScreen.SeeAllMovies>(savedStateHandle)

    override fun canLoadMore(current: MoviesListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: MoviesListModel): SeeAllMoviesUseCaseLoadParams =
        SeeAllMoviesUseCaseLoadParams(
            category = args.category,
            pageNo = current.pageNo + 1,
            movieId = args.movieId
        )

    override suspend fun fetchData(params: SeeAllMoviesUseCaseLoadParams): MoviesListModel =
        useCaseLoad.invoke(params)


    override fun mergeData(
        old: MoviesListModel,
        new: MoviesListModel
    ): MoviesListModel =
        MoviesListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            movies = old.movies + new.movies
        )


    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(args.argId)
    }


}