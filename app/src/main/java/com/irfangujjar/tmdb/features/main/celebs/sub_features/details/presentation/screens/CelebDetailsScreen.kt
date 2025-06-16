package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.screens

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
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.screens.components.CelebDetailsBodyComp
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.viewmodels.CelebDetailsViewModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.viewmodels.states.CelebDetailsState

@Composable
fun CelebDetailsScreen(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    key: HomeNavKey.CelebDetailsNavKey,
    onBackStackPressed: () -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    viewModel: CelebDetailsViewModel = hiltViewModel(),
) {
    viewModel.initialize(key = key)
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.name,
                showBackStack = true,
                onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                CelebDetailsState.Loading -> CustomLoading()
                is CelebDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.loadCelebDetails()
                    }
                )

                is CelebDetailsState.Loaded -> CelebDetailsBodyComp(
                    preview = false,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    celebDetails = state.celebDetails,
                    onMovieItemTapped = { movieId, title, posterPath, backdropPath ->
                        onNavigateToMovieDetails(
                            HomeNavKey.MovieDetailsNavKey(
                                movieId = movieId,
                                title = title,
                                posterPath = posterPath,
                                backdropPath = backdropPath
                            )
                        )
                    },
                    onTvShowItemTapped = { tvId, name, posterPath, backdropPath ->
                        onNavigateToTvShowDetails(
                            HomeNavKey.TvShowDetailsNavKey(
                                tvId = tvId,
                                name = name,
                                posterPath = posterPath,
                                backdropPath = backdropPath
                            )
                        )
                    }
                )
            }
        }
    }
}