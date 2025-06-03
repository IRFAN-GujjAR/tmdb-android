package com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemHorizontalTopRated
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel


@Composable
fun TrendingCelebs(preview: Boolean, celebs: List<CelebModel>,onSeeAllClick:()-> Unit) {
    Column {
        TextRow(title = "Trending", onSeeAllClick = onSeeAllClick)
        LazyRow(modifier = Modifier.padding(top = 4.dp),
            contentPadding = PaddingValues(
                horizontal = ScreenPadding.getHorizontalPadding(),
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(4) {outerIndex->
               Column(modifier = Modifier.width(310.dp),
                   verticalArrangement = Arrangement.spacedBy(12.dp)) {
                   repeat(4) {innerIndex->
                       var itemIndex = innerIndex
                       if (outerIndex != 0) {
                           itemIndex = (outerIndex * 4) + innerIndex
                       }
                       TrendingCelebItem(
                           preview = preview,
                           celeb = celebs[itemIndex]
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
            TrendingCelebs(preview = true, celebs = List(20) { CelebModel.dummyData() },
                onSeeAllClick = {})
        }
    }
}