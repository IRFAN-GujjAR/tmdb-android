package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels

import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadTvShows
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TMDBMediaListTvShowsViewModel @Inject constructor(
    private val useCase: TMDBMediaListUseCaseLoadTvShows,
    private val userSession: UserSession
) : PaginatedViewModel<TvShowsListModel, TMDBMediaListUseCaseLoadParams>(
    initialState = TvShowsListModel.dummyData()
) {

    private lateinit var category: TMDBMediaListCategory
    private lateinit var sortBy: SortBy

    fun initializeValues(data: TvShowsListModel, category: TMDBMediaListCategory, sortBy: SortBy) {
        initialize(value = data)
        this.category = category
        this.sortBy = sortBy
    }

    override fun canLoadMore(current: TvShowsListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: TvShowsListModel): TMDBMediaListUseCaseLoadParams =
        TMDBMediaListUseCaseLoadParams(
            userId = userSession.userId!!,
            category = category,
            pageNo = current.pageNo + 1,
            sessionId = userSession.sessionId!!,
            sortBy = sortBy
        )

    override suspend fun fetchData(params: TMDBMediaListUseCaseLoadParams): TvShowsListModel =
        useCase.invoke(params = params)

    override fun mergeData(
        old: TvShowsListModel,
        new: TvShowsListModel
    ): TvShowsListModel =
        TvShowsListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            tvShows = old.tvShows + new.tvShows
        )
}