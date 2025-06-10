package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.search.domain.usecases.SearchTvShowsUseCase
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchTvShowsViewModel @Inject constructor(
    private val useCase: SearchTvShowsUseCase
) : PaginatedViewModel<TvShowsListModel, SearchUseCaseListParams>() {

    var isInitialized: Boolean = false
        private set

    private lateinit var _query: String

    fun initializeValues(query: String, tvShowsList: TvShowsListModel) {
        _query = query
        updateStateValue(tvShowsList)
        isInitialized = true
    }

    override fun initialState(): TvShowsListModel =
        TvShowsListModel.dummyData()


    override fun canLoadMore(current: TvShowsListModel): Boolean =
        current.pageNo < current.totalPages


    override fun nextPageParams(current: TvShowsListModel): SearchUseCaseListParams =
        SearchUseCaseListParams(query = _query, pageNo = current.pageNo + 1)


    override suspend fun fetchData(params: SearchUseCaseListParams): TvShowsListModel =
        useCase.invoke(params)


    override fun mergeData(
        old: TvShowsListModel,
        new: TvShowsListModel,
    ): TvShowsListModel {
        return TvShowsListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            tvShows = old.tvShows + new.tvShows
        )
    }
}