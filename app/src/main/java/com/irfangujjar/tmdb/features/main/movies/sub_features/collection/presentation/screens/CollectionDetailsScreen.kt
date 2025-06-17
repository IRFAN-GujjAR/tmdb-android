package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens

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
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens.components.CollectionDetailsBodyComp
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.viewmodels.CollectionDetailsViewModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.viewmodels.states.CollectionDetailsState


@Composable
fun CollectionDetailsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    onBackPressed: () -> Unit,
    key: HomeNavKey.CollectionDetailsNavKey,
    viewModel: CollectionDetailsViewModel = hiltViewModel(),
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit
) {
    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.name,
                showBackStack = true,
                onBackStackPressed = onBackPressed,
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                CollectionDetailsState.Loading -> CustomLoading()
                is CollectionDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = { viewModel.loadDetails() })

                is CollectionDetailsState.Loaded -> CollectionDetailsBodyComp(
                    preview = preview,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    collectionDetails = state.collectionDetails,
                    onMovieItemTapped = { id, title, posterPath, backdropPath ->
                        onNavigateToMovieDetails(
                            HomeNavKey.MovieDetailsNavKey(
                                movieId = id,
                                title = title,
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