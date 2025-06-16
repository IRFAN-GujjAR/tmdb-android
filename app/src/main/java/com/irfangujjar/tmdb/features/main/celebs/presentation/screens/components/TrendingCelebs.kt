package com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel


@Composable
fun TrendingCelebs(
    preview: Boolean, celebs: List<CelebModel>, onSeeAllClick: () -> Unit,
    onCelebItemTapped: (Int, String) -> Unit
) {
    Column {
        TextRow(title = "Trending", onSeeAllTapped = onSeeAllClick)
        LazyRow(
            modifier = Modifier.padding(top = 4.dp),
            contentPadding = PaddingValues(
                horizontal = ScreenPadding.getHorizontalPadding(),
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(4) { outerIndex ->
                Column(
                    modifier = Modifier.width(310.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(5) { innerIndex ->
                        var itemIndex = innerIndex
                        if (outerIndex != 0) {
                            itemIndex = (outerIndex * 5) + innerIndex
                        }
                        TrendingCelebItem(
                            preview = preview,
                            celeb = celebs[itemIndex],
                            onCelebItemTapped = onCelebItemTapped
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun TrendingCelebsPreview() {
    TMDbTheme {
        Surface {
            TrendingCelebs(
                preview = true, celebs = List(20) { CelebModel.dummyData() },
                onSeeAllClick = {},
                onCelebItemTapped = { _, _ -> })
        }
    }
}