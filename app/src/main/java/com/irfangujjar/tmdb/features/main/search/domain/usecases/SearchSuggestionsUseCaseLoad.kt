package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchSuggestionUseCaseLoadParams

class SearchSuggestionsUseCaseLoad(
    private val repo: SearchRepo
) : UseCase<SearchModel, SearchSuggestionUseCaseLoadParams> {
    override suspend fun invoke(params: SearchSuggestionUseCaseLoadParams): SearchModel =
        repo.loadSearchSuggestions(params.query)
}