package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels

import com.irfangujjar.tmdb.UserSession
import com.irfangujjar.tmdb.core.viewmodels.PaginatedViewModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.TMDBMediaListUseCaseLoadMovies
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.usecases.params.TMDBMediaListUseCaseLoadParams
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TMDBMediaListMoviesViewModel @Inject constructor(
    private val useCase: TMDBMediaListUseCaseLoadMovies,
    private val userSession: UserSession
) : PaginatedViewModel<MoviesListModel, TMDBMediaListUseCaseLoadParams>(
    initialState = MoviesListModel.dummyData()
) {

    private lateinit var category: TMDBMediaListCategory
    private lateinit var sortBy: SortBy

    fun initializeValues(data: MoviesListModel, category: TMDBMediaListCategory, sortBy: SortBy) {
        initialize(value = data)
        this.category = category
        this.sortBy = sortBy
    }

    override fun canLoadMore(current: MoviesListModel): Boolean =
        current.pageNo < current.totalPages

    override fun nextPageParams(current: MoviesListModel): TMDBMediaListUseCaseLoadParams =
        TMDBMediaListUseCaseLoadParams(
            userId = userSession.userId!!,
            category = category,
            pageNo = current.pageNo + 1,
            sessionId = userSession.sessionId!!,
            sortBy = sortBy
        )

    override suspend fun fetchData(params: TMDBMediaListUseCaseLoadParams): MoviesListModel =
        useCase.invoke(params = params)

    override fun mergeData(
        old: MoviesListModel,
        new: MoviesListModel
    ): MoviesListModel =
        MoviesListModel(
            pageNo = new.pageNo,
            totalPages = new.totalPages,
            movies = old.movies + new.movies
        )
}