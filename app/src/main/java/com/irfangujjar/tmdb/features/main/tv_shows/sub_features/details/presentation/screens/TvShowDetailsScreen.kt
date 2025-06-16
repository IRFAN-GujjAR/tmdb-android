package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens

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
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.components.TvShowDetailsBodyComp
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.TvShowDetailsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.states.TvShowDetailsState


@Composable
fun TvShowDetailsScreen(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    key: HomeNavKey.TvShowDetailsNavKey,
    onBackStackPressed: () -> Unit,
    viewModel: TvShowDetailsViewModel = hiltViewModel(),
    onNavigateToSeeAllTvShows: (HomeNavKey.SeeAllTvShowsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
) {

    viewModel.initialize(key.tvId)

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
                TvShowDetailsState.Loading -> CustomLoading()
                is TvShowDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.loadTvShowDetails(tvId = key.tvId)
                    }
                )

                is TvShowDetailsState.Loaded -> TvShowDetailsBodyComp(
                    preview = false,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    tvShowDetails = state.tvShowDetails,
                    onNavigateToSeeAllTvShows = {
                        val argId = viewModel.saveSeeAllTvShowsArg(
                            if (it == TvShowsCategory.DetailsRecommended)
                                state.tvShowDetails.recommendedTvShows!! else
                                state.tvShowDetails.similarTvShows!!
                        )
                        onNavigateToSeeAllTvShows(
                            HomeNavKey.SeeAllTvShowsNavKey(
                                argId = argId,
                                tvId = key.tvId,
                                category = it
                            )
                        )
                    },
                    onNavigateToTvShowDetails = onNavigateToTvShowDetails
                )
            }
        }
    }
}