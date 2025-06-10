package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
    onScrollThresholdReached: () -> Unit = {},
    onItemClicked: (Int) -> Unit
) {
    val padding = ScreenPadding.getPadding(
        outerPaddingValues = outerPadding,
        innerPaddingValues = innerPadding,
        includeEndPadding = false
    )

    if (state != null) {

        val hasAlreadyTriggered = rememberSaveable { mutableStateOf(false) }

        val hasReachedThreshold by remember {
            derivedStateOf {
                isThresholdReached(state)
            }
        }
        LaunchedEffect(hasReachedThreshold) {
            if (hasReachedThreshold && !hasAlreadyTriggered.value) {
                hasAlreadyTriggered.value = true
                onScrollThresholdReached()
            } else if (!hasReachedThreshold) {
                hasAlreadyTriggered.value = false
            }
        }

        //When user reaches at the end of list and tries to scroll
        val overScrollConnection = remember {
            object : NestedScrollConnection {
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    // Only trigger when dragging upward (i.e., user trying to scroll down)
                    if (available.y < 0f) {
                        if (isThresholdReached(state)) {
                            onScrollThresholdReached()
                        }
                    }
                    return Offset.Zero
                }
            }
        }


        LazyColumn(
            state = state,
            contentPadding = padding,
            modifier = Modifier.nestedScroll(overScrollConnection)
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

private fun isThresholdReached(listState: LazyListState): Boolean {
    val lastVisibleItemIndex =
        listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
    val totalItems = listState.layoutInfo.totalItemsCount
    return totalItems > 0 && lastVisibleItemIndex >= totalItems - 5
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
