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
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemHorizontalValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory

@Composable
fun MediaItemsHorizontalList(
    preview: Boolean = false, values: MediaItemsHorizontalListValues,
    title: String,
    onSeeAllClick: () -> Unit,
    onItemTapped:(Int, String, String?, String?)->Unit = { _, _, _, _ -> }
) {
    Column {
        TextRow(title = title, onSeeAllClick = onSeeAllClick)
        LazyRow(
            modifier = Modifier
                .padding(top = 4.dp)
                .height(values.configValues.listViewHeight),
            contentPadding = PaddingValues(
                start = ScreenPadding.getStartPadding(),
                end = ScreenPadding.getEndPadding()
            ),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            items(values.mediaIds.size) { index ->
                MediaItemHorizontal(
                    preview = preview,
                    values = MediaItemHorizontalValues.fromListValues(
                        listValues = values,
                        index = index,
                    ),
                    onItemTapped = onItemTapped
                )
            }
        }
    }
}

@Preview
@Composable
private fun MediaItemsHorizontalPreview() {
    TMDbTheme {
        Surface {
            MediaItemsHorizontalList(
                preview = true,
                values = MediaItemsHorizontalListValues.dummyDataMovie(
                    category = MoviesCategory.Popular,
                    isLandscape = false
                ),
                title = MoviesCategory.Popular.name,
                onSeeAllClick = {},
                onItemTapped = { _, _, _, _ -> }
            )
        }
    }
}

