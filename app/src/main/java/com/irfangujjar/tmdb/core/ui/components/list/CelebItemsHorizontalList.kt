package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemHorizontalValues
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme

@Composable
fun CelebItemsHorizontalList(
    preview: Boolean = false, values: CelebItemsHorizontalListValues,
    title: String? = null,
    onSeeAllClick: (() -> Unit)? = null,
    onItemTapped: (Int, String) -> Unit
) {
    if (title != null && onSeeAllClick != null && values.celebIds.size > 4)
        Column {
            TextRow(title = title, onSeeAllTapped = onSeeAllClick)
            HorizontalRow(values, preview, onItemTapped)
        }
    else
        HorizontalRow(values, preview, onItemTapped)

}

@Composable
private fun HorizontalRow(
    values: CelebItemsHorizontalListValues,
    preview: Boolean,
    onItemTapped: (Int, String) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .padding(top = 4.dp)
            .height(values.config.listViewHeight),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(
            start = ScreenPadding.getStartPadding(),
            end = ScreenPadding.getEndPadding()
        )
    ) {
        items(values.celebIds.size) {
            CelebItemHorizontal(
                preview = preview,
                values = CelebItemHorizontalValues.fromListValues(
                    values = values,
                    index = it
                ),
                onItemTapped = onItemTapped
            )
        }
    }
}

@Preview
@Composable
private fun CelebItemsHorizontalPreview() {
    TMDbTheme {
        Surface {
            CelebItemsHorizontalList(
                preview = true,
                values = CelebItemsHorizontalListValues.dummyData(),
                title = "Popular",
                onSeeAllClick = {},
                onItemTapped = { _, _ -> }
            )
        }
    }
}