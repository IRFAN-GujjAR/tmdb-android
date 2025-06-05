package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParamsAndReturnType
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo

class TrendingSearchUseCaseLoad(
    private val repo: SearchRepo
) : UseCaseWithoutParamsAndReturnType {
    override suspend fun invoke() = repo.loadTrendingSearch()
}