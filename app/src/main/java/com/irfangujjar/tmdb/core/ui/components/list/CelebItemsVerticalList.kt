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
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemVerticalValues
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsVerticalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme

@Composable
fun CelebItemsVerticalList(
    preview: Boolean,
    outerPadding: PaddingValues? = null,
    innerPadding: PaddingValues? = null,
    state: LazyListState? = null,
    onScrollThresholdReached: () -> Unit = {},
    values: CelebItemsVerticalListValues,
    onItemTapped: (Int, String) -> Unit
) {
    val padding = ScreenPadding.getPadding(
        outerPaddingValues = outerPadding,
        innerPaddingValues = innerPadding,
        includeEndPadding = false
    )
    if (state == null) {
        LazyColumn(
            contentPadding = padding
        ) {
            itemContent(
                preview = preview, values = values,
                onItemTapped = onItemTapped
            )
        }
    } else {
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
            itemContent(
                preview = preview, values = values,
                onItemTapped = onItemTapped
            )
        }
    }
}

private fun LazyListScope.itemContent(
    preview: Boolean,
    values: CelebItemsVerticalListValues,
    onItemTapped: (Int, String) -> Unit
) {
    items(values.celebsIds.size) { index ->
        CelebItemVertical(
            preview = preview, values = CelebItemVerticalValues.fromListValues(
                values = values,
                index = index
            ),
            onItemTapped = onItemTapped
        )
        if (index < values.celebsIds.size - 1) {
            CustomDivider(
                startPadding = DividerStartPadding.Zero,
            )
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
private fun CelebItemsVerticalListPreview() {
    TMDbTheme {
        Surface {
            CelebItemsVerticalList(
                preview = true,
                values = CelebItemsVerticalListValues.dummyData(),
                onItemTapped = { id, name -> }
            )
        }
    }
}