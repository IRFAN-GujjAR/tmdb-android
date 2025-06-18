package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens

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
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens.components.SeasonDetailsBodyComp
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.SeasonDetailsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states.SeasonDetailsState


@Composable
fun SeasonDetailsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    onBackPressed: () -> Unit,
    key: HomeNavKey.SeasonDetailsNavKey,
    viewModel: SeasonDetailsViewModel = hiltViewModel()
) {
    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.seasonName, showBackStack = true,
                onBackStackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                SeasonDetailsState.Loading -> CustomLoading()
                is SeasonDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = { viewModel.loadDetails() })

                is SeasonDetailsState.Loaded -> SeasonDetailsBodyComp(
                    preview = preview,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    key=key,
                    seasonDetails = state.seasonDetails
                )
            }
        }
    }
}
