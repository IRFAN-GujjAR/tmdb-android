package com.irfangujjar.tmdb.features.main.search.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.search.presentation.screens.components.CustomSearchBar
import com.irfangujjar.tmdb.features.main.search.presentation.screens.components.TrendingSearches
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    userTheme: UserTheme,
    outerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState?,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    Scaffold(topBar = {
        if (viewModel.showSearchBar)
            CustomSearchBar(
                value = viewModel.query,
                onValueChanged = {
                    viewModel.updateQuery(it)
                },
                hideSearchBar = { viewModel.updateShowSearchBar(false) }
            )
        else
            CustomTopAppBar(
                title = "Search",
                actions = {
                    IconButton(onClick = { viewModel.updateShowSearchBar(true) }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                }
            )
    }) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state.value) {
                SearchState.Trending -> TrendingSearches(
                    userTheme = userTheme,
                    outerPaddingValues = outerPadding,
                    innerPaddingValues = innerPadding,
                    viewModel = viewModel,
                    snackbarHostState = snackbarHostState
                )

                SearchState.Suggestions -> {}
                SearchState.Details -> {

                }
            }
        }
    }
}
