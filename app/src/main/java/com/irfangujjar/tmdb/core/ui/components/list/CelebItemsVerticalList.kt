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
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemVerticalValues
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsVerticalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme

@Composable
fun CelebItemsVerticalList(
    preview: Boolean,
    outerPadding: PaddingValues? = null,
    innerPadding: PaddingValues? = null,
    state: LazyListState? = null,
    values: CelebItemsVerticalListValues
) {
    val padding = ScreenPadding.getPadding(
        outerPaddingValues = outerPadding,
        innerPaddingValues = innerPadding,
        includeEndPadding = false
    )
    if (state == null)
        LazyColumn(
            contentPadding = padding
        ) {
            itemContent(preview = preview, values = values)
        }
    else
        LazyColumn(
            state = state,
            contentPadding = padding
        ) {
            itemContent(preview = preview, values = values)
        }
}

private fun LazyListScope.itemContent(
    preview: Boolean,
    values: CelebItemsVerticalListValues,
) {
    items(values.celebsIds.size) { index ->
        CelebItemVertical(
            preview = preview, values = CelebItemVerticalValues.fromListValues(
                values = values,
                index = index
            )
        )
        if (index < values.celebsIds.size - 1) {
            CustomDivider(
                startPadding = DividerStartPadding.Zero,
            )
        }
    }
}

@Preview
@Composable
private fun CelebItemsVerticalListPreview() {
    TMDbTheme {
        Surface {
            CelebItemsVerticalList(
                preview = true,
                values = CelebItemsVerticalListValues.dummyData()
            )
        }
    }
}