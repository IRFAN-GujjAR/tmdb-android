package com.irfangujjar.tmdb.features.main.search.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.features.main.search.presentation.screens.components.CustomSearchBar
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {

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
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}
