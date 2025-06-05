package com.irfangujjar.tmdb.features.main.search.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutParams
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.domain.repos.SearchRepo
import kotlinx.coroutines.flow.Flow

class TrendingSearchUseCaseWatch(
    private val repo: SearchRepo
) : UseCaseWithoutParams<Flow<SearchModel?>> {
    override suspend fun invoke(): Flow<SearchModel?> = repo.getTrendingSearchFlow()
}