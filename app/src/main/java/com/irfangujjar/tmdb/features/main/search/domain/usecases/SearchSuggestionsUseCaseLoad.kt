package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCase
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import com.irfangujjar.tmdb.features.main.search.domain.usecases.params.SearchSuggestionParams

class SearchSuggestionsUseCaseLoad(
    private val repo: SearchRepo
) : UseCase<SearchModel, SearchSuggestionParams> {
    override suspend fun invoke(params: SearchSuggestionParams): SearchModel =
        repo.loadSearchSuggestions(params.query)
}