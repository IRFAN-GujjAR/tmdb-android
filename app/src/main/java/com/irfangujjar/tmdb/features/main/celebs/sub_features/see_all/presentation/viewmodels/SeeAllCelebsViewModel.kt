package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.viewmodels

import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModelWithKey
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.SeeAllCelebsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.params.SeeAllCelebsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.nav_args_holder.SeeAllCelebsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllCelebsViewModel @Inject constructor(
    private val navArgsHolder: SeeAllCelebsNavArgsHolder,
    private val useCaseLoad: SeeAllCelebsUseCaseLoad
) : PaginatedViewModelWithKey<HomeNavKey.SeeAllCelebsNavKey, CelebsListModel, SeeAllCelebsUseCaseLoadParams>(
    initialState = CelebsListModel.dummyData(),
) {

    fun initialize(key: HomeNavKey.SeeAllCelebsNavKey) {
        initializeWithNavArgsHolder(key = key, navArgsHolder = navArgsHolder)
    }

    override fun canLoadMore(current: CelebsListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: CelebsListModel): SeeAllCelebsUseCaseLoadParams =
        SeeAllCelebsUseCaseLoadParams(
            category = key!!.category,
            pageNo = current.pageNo + 1,
        )

    override suspend fun fetchData(params: SeeAllCelebsUseCaseLoadParams): CelebsListModel =
        useCaseLoad.invoke(params)


    override fun mergeData(
        old: CelebsListModel,
        new: CelebsListModel
    ): CelebsListModel =
        CelebsListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            celebrities = old.celebrities + new.celebrities
        )

    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(key!!.argId)
    }
}