package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.irfangujjar.tmdb.core.api.ResultWrapper
import com.irfangujjar.tmdb.core.api.safeApiCall
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.viewmodels.ViewModelWithKey
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.MovieDetailsUseCaseLoad
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.usecases.params.MovieDetailsUseCaseLoadParams
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.states.MovieDetailsState
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.nav_args_holder.SeeAllMoviesNavArgsHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCase: MovieDetailsUseCaseLoad,
    private val seeAllMoviesNavArgsHolder: SeeAllMoviesNavArgsHolder
) : ViewModelWithKey<HomeNavKey.MovieDetailsNavKey>() {

    private val _state = MutableStateFlow<MovieDetailsState>(MovieDetailsState.Loading)
    val state: StateFlow<MovieDetailsState> = _state

    override fun doInitial() = loadMovieDetails()

    fun loadMovieDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = MovieDetailsState.Loading
            val result = safeApiCall {
                useCase.invoke(
                    params = MovieDetailsUseCaseLoadParams(
                        movieId = key!!.movieId
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

    fun saveSeeAllMoviesArg(moviesList: MoviesListModel): String =
        seeAllMoviesNavArgsHolder.saveArgData(moviesList)


}