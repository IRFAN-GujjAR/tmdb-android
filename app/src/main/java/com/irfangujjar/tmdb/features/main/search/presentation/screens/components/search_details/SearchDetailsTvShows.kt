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
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

@Composable
fun SearchDetailsTvShows(
    preview: Boolean,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp,
    listState: LazyListState?,
    tvShowsList: TvShowsListModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        MediaItemsVerticalList(
            state = listState,
            preview = preview,
            outerPadding = PaddingValues(
                top = topPadding,
                bottom = bottomPadding
            ),
            values = MediaItemsVerticalListValues.fromTvShows(tvShows = tvShowsList.tvShows)
        ) {

        }
    }
}

@Preview
@Composable
private fun SearchDetailsTvShowsPreview() {
    TMDbTheme {
        Surface {
            SearchDetailsTvShows(
                preview = true,
                bottomPadding = 0.dp,
                listState = null,
                tvShowsList = TvShowsListModel.dummyData()
            )
        }
    }
}