package com.irfangujjar.tmdb.features.main.movies.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontal
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MoviesCategories


@Composable
fun MoviesScreen(preview: Boolean = false, paddingValues: PaddingValues) {
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
                            innerPaddingValues = innerPadding,
                            includeStartPadding = false,
                            includeEndPadding = false
                        )
                    )
            ) {
                MediaItemsHorizontal(
                    preview = preview,
                    values = MediaItemsHorizontalValues.dummyDataMovie(
                        category =
                            MoviesCategories.Popular,
                        isLandscape = false
                    ),
                    title = MoviesCategories.Popular.name
                ) {

                }
                CustomDivider()
                MediaItemsHorizontal(
                    preview = preview,
                    values = MediaItemsHorizontalValues.dummyDataMovie(
                        category =
                            MoviesCategories.InTheatres,
                        isLandscape = true
                    ),
                    title = MoviesCategories.InTheatres.name
                ) {

                }
                CustomDivider()
                MediaItemsHorizontal(
                    preview = preview,
                    values = MediaItemsHorizontalValues.dummyDataMovie(
                        category =
                            MoviesCategories.Trending,
                        isLandscape = false
                    ),
                    title = MoviesCategories.Trending.name
                ) {

                }
                CustomDivider()
                MediaItemsHorizontal(
                    preview = preview,
                    values = MediaItemsHorizontalValues.dummyDataMovie(
                        category =
                            MoviesCategories.Upcoming,
                        isLandscape = false
                    ),
                    title = MoviesCategories.Upcoming.name
                ) {

                }
            }
        }
    }
}


@Preview
@Composable
private fun PreviewMovieScreen() {
    TMDbTheme {
        MoviesScreen(
            paddingValues = PaddingValues(),
            preview = true
        )
    }
}