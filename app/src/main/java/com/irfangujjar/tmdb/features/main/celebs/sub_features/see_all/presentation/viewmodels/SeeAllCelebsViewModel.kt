package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.SeeAllCelebsUseCaseLoad
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.params.SeeAllCelebsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.nav_args_holder.SeeAllCelebsNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeeAllCelebsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllCelebsNavArgsHolder,
    private val useCaseLoad: SeeAllCelebsUseCaseLoad
) : PaginatedViewModel<CelebsListModel, SeeAllCelebsUseCaseLoadParams>(
    initialState = getInitialState<CelebsListModel, HomeScreen.SeeAllCelebs>(
        savedStateHandle = savedStateHandle,
        navArgsHolder = navArgsHolder
    )!!
) {
    val args = getArgs<HomeScreen.SeeAllCelebs>(savedStateHandle)

    override fun canLoadMore(current: CelebsListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: CelebsListModel): SeeAllCelebsUseCaseLoadParams =
        SeeAllCelebsUseCaseLoadParams(
            category = args.category,
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
        navArgsHolder.removeArg(args.argId)
    }

}