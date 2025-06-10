package com.irfangujjar.tmdb.features.main.search.presentation.screens.components.search_details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel


@Composable
fun SearchDetailsMovies(
    preview: Boolean,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp, moviesList: MoviesListModel,
    listState: LazyListState?,

    onItemClicked: (Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MediaItemsVerticalList(
            preview = preview,
            state = listState,
            outerPadding = PaddingValues(
                top = topPadding,
                bottom = bottomPadding
            ),
            values = MediaItemsVerticalListValues.fromMovies(movies = moviesList.movies)
        ) {
            onItemClicked(it)
        }
    }
}

@Preview
@Composable
private fun SearchDetailsMoviesPreview() {
    TMDbTheme {
        Surface {
            SearchDetailsMovies(
                preview = true,
                bottomPadding = 0.dp,
                listState = null,
                moviesList = MoviesListModel.dummyData(),
            ) {

            }
        }
    }
}