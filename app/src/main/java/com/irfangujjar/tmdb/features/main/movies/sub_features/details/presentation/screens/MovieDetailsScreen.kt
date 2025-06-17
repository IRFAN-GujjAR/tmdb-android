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
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.components.MovieDetailsBody
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.MovieDetailsViewModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.viewmodels.states.MovieDetailsState

@Composable
fun MovieDetailsScreen(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    key: HomeNavKey.MovieDetailsNavKey,
    onBackStackPressed: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onNavigateToSeeAllMovies: (HomeNavKey.SeeAllMoviesNavKey) -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToCollectionDetails: (HomeNavKey.CollectionDetailsNavKey) -> Unit,
    onNavigateToCastCrewDetails: (HomeNavKey.CastCrewNavKey) -> Unit
) {
    viewModel.initialize(key = key)

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
                        viewModel.loadMovieDetails()
                    }
                )

                is MovieDetailsState.Loaded -> MovieDetailsBody(
                    preview = false,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    movieDetails = state.movieDetails,
                    onNavigateToSeeAllMovies = {
                        val argId = viewModel.saveSeeAllMoviesArg(
                            if (it == MoviesCategory.DetailsRecommended)
                                state.movieDetails.recommendedMovies!! else
                                state.movieDetails.similarMovies!!
                        )
                        onNavigateToSeeAllMovies(
                            HomeNavKey.SeeAllMoviesNavKey(
                                argId = argId,
                                movieId = key.movieId,
                                category = it
                            )
                        )
                    },
                    onNavigateToMovieDetails = onNavigateToMovieDetails,
                    onCastCrewSeeAllClick = {
                        val argId = viewModel.saveCastCrewArgs(it)
                        onNavigateToCastCrewDetails(
                            HomeNavKey.CastCrewNavKey(
                                argId = argId
                            )
                        )
                    },
                    onNavigateToCollectionDetails = onNavigateToCollectionDetails
                )
            }
        }
    }
}