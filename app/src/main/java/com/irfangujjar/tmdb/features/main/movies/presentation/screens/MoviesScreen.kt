package com.irfangujjar.tmdb.features.main.movies.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar


@Composable
fun MoviesScreen(paddingValues: PaddingValues) {
    val scrollState = rememberScrollState()
    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Movies"
        )
    }) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .padding(
                        ScreenPadding.getPadding(
                            outerPaddingValues = paddingValues,
                            innerPaddingValues = innerPadding
                        )
                    )
            ) {
                for (i in 1..100) {
                    Text("Hello World : $i")
                }
            }
        }
    }
}