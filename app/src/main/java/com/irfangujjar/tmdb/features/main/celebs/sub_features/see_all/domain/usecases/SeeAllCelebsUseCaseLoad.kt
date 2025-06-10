package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.repos.SeeAllCelebsRepo
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.domain.usecases.params.SeeAllCelebsUseCaseLoadParams

class SeeAllCelebsUseCaseLoad(
    private val repo: SeeAllCelebsRepo
) : UseCase<CelebsListModel, SeeAllCelebsUseCaseLoadParams> {
    override suspend fun invoke(params: SeeAllCelebsUseCaseLoadParams): CelebsListModel =
        repo.loadCelebs(category = params.category, pageNo = params.pageNo)
}