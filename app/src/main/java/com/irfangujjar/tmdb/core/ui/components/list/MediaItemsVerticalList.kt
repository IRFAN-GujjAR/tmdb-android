package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerStartPadding
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemVerticalValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType

@Composable
fun MediaItemsVerticalList(
    preview: Boolean,
    outerPadding: PaddingValues? = null,
    innerPadding: PaddingValues? = null,
    values: MediaItemsVerticalListValues,
    state: LazyListState? = null,
    onItemClicked: (Int) -> Unit
) {
    val padding = ScreenPadding.getPadding(
        outerPaddingValues = outerPadding,
        innerPaddingValues = innerPadding,
        includeEndPadding = false
    )

    if (state != null) {
        LazyColumn(
            state = state,
            contentPadding = padding
        ) {
            itemContent(values, preview, onItemClicked)
        }
    } else {
        LazyColumn(
            contentPadding = padding
        ) {
            itemContent(values, preview, onItemClicked)
        }
    }
}

private fun LazyListScope.itemContent(
    values: MediaItemsVerticalListValues,
    preview: Boolean,
    onItemClicked: (Int) -> Unit
) {
    items(values.mediaIds.size) { index ->
        MediaItemVertical(
            preview = preview,
            values = MediaItemVerticalValues.fromListValues(values, index)
        ) {
            onItemClicked(it)
        }
        if (index < values.mediaIds.size - 1) {
            CustomDivider(startPadding = DividerStartPadding.Zero)
        }
    }
}


@Preview
@Composable
private fun MediaItemsVerticalListPreview() {
    TMDbTheme {
        Surface {
            MediaItemsVerticalList(
                preview = true,
                values = MediaItemsVerticalListValues.dummyData(MediaType.Movie)
            ) {}
        }
    }
}
