package com.irfangujjar.tmdb.features.main.celebs.domain.usecases

import com.irfangujjar.tmdb.core.usecase.UseCaseWithoutAsyncAndParams
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import com.irfangujjar.tmdb.features.main.celebs.domain.repos.CelebsRepo
import kotlinx.coroutines.flow.Flow

class CelebsUseCaseWatch(private val repo: CelebsRepo) :
    UseCaseWithoutAsyncAndParams<Flow<CelebsModel?>> {
    override fun invoke(): Flow<CelebsModel?> = repo.getCelebsFlow()
}