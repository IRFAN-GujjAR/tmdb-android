package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.components.MovieDetailsBody
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.MovieDetailsViewModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.states.MovieDetailsState

@Composable
fun MovieDetailsScreen(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    key: HomeNavKey.MovieDetailsNavKey,
    onBackStackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    viewModel.initialize(movieId = key.movieId)

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.title,
                showBackStack = true,
                onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                MovieDetailsState.Loading -> CustomLoading()
                is MovieDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.loadMovieDetails(movieId = key.movieId)
                    }
                )

                is MovieDetailsState.Loaded -> MovieDetailsBody(
                    preview = false,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    movieDetails = state.movieDetails
                )
            }
        }
    }
}