package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchUseCaseListParams

class SearchCelebsUseCase(
    private val repo: SearchRepo
) : UseCase<CelebsListModel, SearchUseCaseListParams> {
    override suspend fun invoke(params: SearchUseCaseListParams): CelebsListModel =
        repo.searchCelebs(query = params.query, pageNo = params.pageNo)
}