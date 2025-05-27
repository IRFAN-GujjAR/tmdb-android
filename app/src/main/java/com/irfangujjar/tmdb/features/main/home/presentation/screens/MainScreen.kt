package com.irfangujjar.tmdb.features.main.home.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.features.main.home.presentation.viewmodels.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel<MainViewModel>()) {
    Surface(modifier = Modifier.fillMaxSize()) {

    }
}