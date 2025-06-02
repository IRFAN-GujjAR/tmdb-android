package com.irfangujjar.tmdb.features.main.tmdb.presentation.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.theme.presentation.viewmodel.UserThemeViewModel


@Composable
fun TMDBScreen(
    paddingValues: PaddingValues,
    userTheme: UserTheme,
    viewModel: UserThemeViewModel = hiltViewModel()
) {
    val isSystemDarkThem = isSystemInDarkTheme()
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                when (userTheme) {
                    UserTheme.SYSTEM -> viewModel.saveUserTheme(userTheme = if (isSystemDarkThem) UserTheme.LIGHT else UserTheme.DARK)
                    UserTheme.LIGHT -> viewModel.saveUserTheme(UserTheme.DARK)
                    UserTheme.DARK -> viewModel.saveUserTheme(UserTheme.LIGHT)
                }
            }) {
                Text("Toggle Theme")
            }
        }
    }
}