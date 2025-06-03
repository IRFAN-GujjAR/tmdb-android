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
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme

@Composable
fun CelebItemsHorizontal(preview: Boolean = false, values: CelebItemsHorizontalValues,
                         title: String?=null,
                         onSeeAllClick:(()-> Unit)?=null, ) {
    if(title!=null && onSeeAllClick!=null)
        Column {
            TextRow(title = title, onSeeAllClick = onSeeAllClick)
            HorizontalRow(values, preview)
        }
    else
        HorizontalRow(values, preview)

}

@Composable
private fun HorizontalRow(
    values: CelebItemsHorizontalValues,
    preview: Boolean
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
                )
            )
        }
    }
}

@Preview
@Composable
private fun CelebItemsHorizontalPreview() {
    TMDbTheme {
        Surface {
            CelebItemsHorizontal(
                preview = true,
                values = CelebItemsHorizontalValues.dummyData(),
                title = "Popular",
                onSeeAllClick = {}
            )
        }
    }
}