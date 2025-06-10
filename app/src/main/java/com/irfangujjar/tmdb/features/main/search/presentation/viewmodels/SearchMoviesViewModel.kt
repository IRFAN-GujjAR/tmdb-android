package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.search.domain.usecases.SearchMoviesUseCase
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val useCase: SearchMoviesUseCase
) : PaginatedViewModel<MoviesListModel, SearchUseCaseListParams>() {

    var isInitialized: Boolean = false
        private set

    private lateinit var _query: String

    fun initializeValues(query: String, moviesList: MoviesListModel) {
        _query = query
        updateStateValue(moviesList)
        isInitialized = true
    }

    override fun initialState(): MoviesListModel =
        MoviesListModel.dummyData()


    override fun canLoadMore(current: MoviesListModel): Boolean =
        current.pageNo < current.totalPages


    override fun nextPageParams(current: MoviesListModel): SearchUseCaseListParams =
        SearchUseCaseListParams(query = _query, pageNo = current.pageNo + 1)


    override suspend fun fetchData(params: SearchUseCaseListParams): MoviesListModel =
        useCase.invoke(params)


    override fun mergeData(
        old: MoviesListModel,
        new: MoviesListModel,
    ): MoviesListModel {
        return MoviesListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            movies = old.movies + new.movies
        )
    }
}