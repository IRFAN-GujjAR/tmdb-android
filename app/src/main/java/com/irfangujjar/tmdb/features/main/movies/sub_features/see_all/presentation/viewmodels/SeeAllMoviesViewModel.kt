package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.SeeAllMoviesUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.domain.usecases.params.SeeAllMoviesUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder.SeeAllMoviesNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllMoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val navArgsHolder: SeeAllMoviesNavArgsHolder,
    private val useCaseLoad: SeeAllMoviesUseCaseLoad
) : ViewModel() {

    val args = savedStateHandle.toRoute<HomeScreen.SeeAllMovies>()
    private val _moviesListState =
        MutableStateFlow<MoviesListModel>(navArgsHolder.getArgData(args.argsId)!!)
    val moviesListState: StateFlow<MoviesListModel> = _moviesListState

    private var isLoadingMore = false
    var errorMessage = ""
        private set
    var showSnackBarErrorMessage by mutableStateOf(false)
        private set

    fun clearSnackBarError() {
        showSnackBarErrorMessage = false
        errorMessage = ""
    }


    fun onLoadMore() {
        if (!isLoadingMore && moviesListState.value.pageNo < moviesListState.value.totalPages) {
            isLoadingMore = true
            viewModelScope.launch(Dispatchers.IO) {
                val result = safeApiCall {
                    useCaseLoad.invoke(
                        params = SeeAllMoviesUseCaseLoadParams(
                            category = args.category,
                            movieId = args.movieId,
                            pageNo = moviesListState.value.pageNo + 1
                        )
                    )
                }
                when (result) {
                    is ResultWrapper.Error -> {
                        errorMessage = result.errorEntity.message
                        showSnackBarErrorMessage = true
                    }

                    is ResultWrapper.Success<MoviesListModel> -> {
                        val moviesList = MoviesListModel(
                            pageNo = result.data.pageNo,
                            totalPages = result.data.totalPages,
                            movies = moviesListState.value.movies + result.data.movies
                        )
                        _moviesListState.value = moviesList
                    }
                }
            }.invokeOnCompletion {
                isLoadingMore = false
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        navArgsHolder.removeArg(args.argsId)
    }
}