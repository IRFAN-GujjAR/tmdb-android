package com.irfangujjar.tmdb.features.main.search.presentation.viewmodels

import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.search.domain.usecases.SearchCelebsUseCase
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchCelebsViewModel @Inject constructor(
    private val useCase: SearchCelebsUseCase
) : PaginatedViewModel<CelebsListModel, SearchUseCaseListParams>(
    initialState =
        CelebsListModel.dummyData()
) {

    private lateinit var _query: String

    fun initializeValues(query: String, celebsList: CelebsListModel) {
        if (!isInitialized) {
            _query = query
            initialize(value = celebsList)
        }
    }


    override fun canLoadMore(current: CelebsListModel): Boolean =
        current.pageNo < current.totalPages


    override fun nextPageParams(current: CelebsListModel): SearchUseCaseListParams =
        SearchUseCaseListParams(query = _query, pageNo = current.pageNo + 1)


    override suspend fun fetchData(params: SearchUseCaseListParams): CelebsListModel =
        useCase.invoke(params)


    override fun mergeData(
        old: CelebsListModel,
        new: CelebsListModel,
    ): CelebsListModel {
        return CelebsListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            celebrities = old.celebrities + new.celebrities
        )
    }
}