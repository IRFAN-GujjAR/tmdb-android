package com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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
        LazyHorizontalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            rows = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            items(celebs) {
                TrendingCelebItem(
                    preview = preview,
                    celeb = it,
                    onCelebItemTapped = onCelebItemTapped
                )
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