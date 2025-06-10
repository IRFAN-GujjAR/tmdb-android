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
import com.irfangujjar.tmdb.core.ui.components.list.CelebItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsVerticalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel


@Composable
fun SearchDetailsCelebs(
    preview: Boolean,
    listState: LazyListState?,
    topPadding: Dp = 0.dp,
    bottomPadding: Dp, celebsList: CelebsListModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        CelebItemsVerticalList(
            preview = preview,
            state = listState,
            outerPadding = PaddingValues(top = topPadding, bottom = bottomPadding),
            values = CelebItemsVerticalListValues.fromCelebs(celebs = celebsList.celebrities)
        )
    }
}

@Preview
@Composable
private fun SearchDetailsCelebsPreview() {
    TMDbTheme {
        Surface {
            SearchDetailsCelebs(
                preview = true,
                bottomPadding = 0.dp,
                listState = null,
                celebsList = CelebsListModel.dummyData()
            )
        }
    }
}