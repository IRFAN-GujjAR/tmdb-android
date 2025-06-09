package com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.viewmodels.SeeAllMoviesViewModel

@Composable
fun SeeAllMoviesScreen(
    viewModel: SeeAllMoviesViewModel = hiltViewModel(),
) {
    val moviesList = viewModel.moviesList!!
    Scaffold { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(moviesList.totalPages.toString())
            }
        }
    }
}