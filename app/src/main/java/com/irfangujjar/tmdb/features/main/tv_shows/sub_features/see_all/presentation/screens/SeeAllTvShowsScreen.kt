package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.viewmodels.SeeAllTvShowsViewModel

@Composable
fun SeeAllTvShowsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    key: HomeNavKey.SeeAllTvShowsNavKey,
    viewModel: SeeAllTvShowsViewModel = hiltViewModel(),
    onBackStackPressed: () -> Unit
) {
    val listState = rememberLazyListState()

    viewModel.initialize(key = key)

    val tvShows = viewModel.state.collectAsState().value.tvShows

    if (viewModel.showAlert) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(viewModel.alertMessage)
            viewModel.clearAlert()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.category.name,
                showBackStack = true,
                onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            MediaItemsVerticalList(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                values = MediaItemsVerticalListValues.fromTvShows(
                    tvShows = tvShows
                ),
                state = listState,
                onScrollThresholdReached = {
                    viewModel.loadMore()
                }
            ) { }
        }
    }
}