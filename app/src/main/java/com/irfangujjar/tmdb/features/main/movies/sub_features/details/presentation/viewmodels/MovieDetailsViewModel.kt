package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.MovieDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.params.MovieDetailsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.states.MovieDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCase: MovieDetailsUseCaseLoad,
) : ViewModel() {

    private val _state = MutableStateFlow<MovieDetailsState>(MovieDetailsState.Loading)
    val state: StateFlow<MovieDetailsState> = _state

    private var isInitialized = false

    fun initialize(movieId: Int) {
        if (!isInitialized) {
            isInitialized = true
            loadMovieDetails(movieId = movieId)
        }
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MovieDetailsState.Loading
            val result = safeApiCall {
                useCase.invoke(
                    params = MovieDetailsUseCaseLoadParams(
                        movieId = movieId
                    )
                )
            }
            when (result) {
                is ResultWrapper.Error -> {
                    Log.d(
                        "MovieDetailsViewModel",
                        "Error loading movie details: ${result.errorEntity}"
                    )
                    _state.value = MovieDetailsState.Error(
                        error = result.errorEntity
                    )
                }

                is ResultWrapper.Success<MovieDetailsModel> -> _state.value =
                    MovieDetailsState.Loaded(
                        movieDetails = result.data
                    )
            }
        }
    }
}