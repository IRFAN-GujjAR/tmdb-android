package com.irfangujjar.tmdb.features.main.search.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar


@Composable
fun SearchScreen(paddingValues: PaddingValues) {
    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Search"
        )
    }) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(bottom = paddingValues.calculateBottomPadding()),
                contentAlignment = Alignment.Center
            ) {
                Text("Search")
            }
        }
    }
}