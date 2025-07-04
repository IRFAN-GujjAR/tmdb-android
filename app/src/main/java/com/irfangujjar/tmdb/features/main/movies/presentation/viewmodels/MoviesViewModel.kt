package com.irfangujjar.tmdb.features.main.movies.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithErrorAlerts
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.domain.usecases.MoviesUseCaseWatch
import com.irfangujjar.tmdb.features.main.movies.presentation.viewmodels.state.MoviesState
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder.SeeAllMoviesNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCaseWatch: MoviesUseCaseWatch,
    private val moviesUseCaseLoad: MoviesUseCaseLoad,
    private val seeAllMoviesNavArgsHolder: SeeAllMoviesNavArgsHolder
) : ViewModelWithErrorAlerts() {

    private val _state: MutableStateFlow<MoviesState> = MutableStateFlow(MoviesState.Loading)
    val state: StateFlow<MoviesState> = _state

    var isRefreshing by mutableStateOf(false)
        private set

    private val firstEmissionDeferred = CompletableDeferred<Unit>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            watchMovies()
        }
        viewModelScope.launch(Dispatchers.IO) {
            loadMovies()
        }
    }

    private suspend fun watchMovies() {
        moviesUseCaseWatch.invoke().collect { movies ->
            if (movies != null) {
                _state.value = MoviesState.Loaded(movies = movies)
            }
            if (!firstEmissionDeferred.isCompleted)
                firstEmissionDeferred.complete(Unit)
        }
    }

    private suspend fun loadMovies() {
        val result = safeApiCall {
            if (state.value is MoviesState.Error) {
                _state.value = MoviesState.Loading
            }
            moviesUseCaseLoad.invoke()
        }
        firstEmissionDeferred.await()
        if (result is ResultWrapper.Error) {
            if (state.value is MoviesState.Loaded) {
                showAlert(message = result.errorEntity.message)
                _state.value = MoviesState.ErrorWithCache(
                    movies = (state.value as MoviesState.Loaded).movies,
                    error = result.errorEntity
                )
            } else {
                _state.value = MoviesState.Error(error = result.errorEntity)
            }
        }
    }

    fun retry() {
        viewModelScope.launch(Dispatchers.IO) {
            loadMovies()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            isRefreshing = true
            loadMovies()
            isRefreshing = false
        }
    }


    fun saveSeeAllMoviesArg(category: MoviesCategory, movies: MoviesModel): String =
        when (category) {
            MoviesCategory.Popular -> seeAllMoviesNavArgsHolder.saveArgData(movies.popular)
            MoviesCategory.InTheatres -> seeAllMoviesNavArgsHolder.saveArgData(movies.inTheatres)
            MoviesCategory.Trending -> seeAllMoviesNavArgsHolder.saveArgData(movies.trending)
            MoviesCategory.TopRated -> seeAllMoviesNavArgsHolder.saveArgData(movies.topRated)
            else -> seeAllMoviesNavArgsHolder.saveArgData(movies.upcoming)
        }


}