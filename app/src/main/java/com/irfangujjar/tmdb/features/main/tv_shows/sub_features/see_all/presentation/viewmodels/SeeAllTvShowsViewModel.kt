package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModelWithKey
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.SeeAllTvShowsUseCaseLoad
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.domain.usecases.params.SeeAllTvShowsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.nav_args_holder.SeeAllTvShowsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllTvShowsViewModel @Inject constructor(
    private val navArgsHolder: SeeAllTvShowsNavArgsHolder,
    private val useCaseLoad: SeeAllTvShowsUseCaseLoad
) : PaginatedViewModelWithKey<HomeNavKey.SeeAllTvShowsNavKey, TvShowsListModel, SeeAllTvShowsUseCaseLoadParams>(
    initialState = TvShowsListModel.dummyData(),
) {


    fun initialize(key: HomeNavKey.SeeAllTvShowsNavKey) {
        initializeWithNavArgsHolder(key = key, navArgsHolder = navArgsHolder)
    }

    override fun canLoadMore(current: TvShowsListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: TvShowsListModel): SeeAllTvShowsUseCaseLoadParams =
        SeeAllTvShowsUseCaseLoadParams(
            category = key!!.category,
            pageNo = current.pageNo + 1,
            tvId = key!!.tvId
        )

    override suspend fun fetchData(params: SeeAllTvShowsUseCaseLoadParams): TvShowsListModel =
        useCaseLoad.invoke(params)


    override fun mergeData(
        old: TvShowsListModel,
        new: TvShowsListModel
    ): TvShowsListModel =
        TvShowsListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            tvShows = old.tvShows + new.tvShows
        )


    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(key!!.argId)
    }
}