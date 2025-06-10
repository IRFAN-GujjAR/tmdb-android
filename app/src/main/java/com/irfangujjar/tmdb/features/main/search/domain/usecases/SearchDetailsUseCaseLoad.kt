package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchDetailsUseCaseLoadParams

class SearchDetailsUseCaseLoad(
    private val repo: SearchRepo
) : UseCase<SearchDetailsModel, SearchDetailsUseCaseLoadParams> {
    override suspend fun invoke(params: SearchDetailsUseCaseLoadParams): SearchDetailsModel =
        repo.loadSearchDetails(query = params.query)
}