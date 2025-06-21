package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.components

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.SeasonModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.network.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel

@Composable
fun TvShowDetailsSeasonComp(
    preview: Boolean,
    tvShowPosterPath: String?,
    seasons: List<SeasonModel>,
    onSeeAllPressed: () -> Unit,
    onSeasonItemPressed: (SeasonModel) -> Unit
) {

    val showSeeAll = seasons.size >= 5
    Column {
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = if (showSeeAll) DividerBottomPadding.Half else DividerBottomPadding.OneAndHalf
        )
        TextRow(title = "Seasons", onSeeAllTapped = if (showSeeAll) onSeeAllPressed else null)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(
                horizontal = ScreenPadding.getHorizontalPadding()
            )
        ) {
            val config = MediaItemsHorizontalListConfigValues.fromDefault()
            items(seasons) { season ->
                Column(modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onSeasonItemPressed(season)
                    })
                }) {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.TvShow,
                        imageUrl = season.posterPath ?: tvShowPosterPath,
                        width = config.listItemWidth,
                        height = config.imageHeight,
                        contentScale = ContentScale.Crop,
                        placeHolderSize = 72.dp,
                        posterSize = PosterSizes.w185,
                    )
                    Text(
                        season.name,
                        fontSize = 13.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun TvShowDetailsSeasonCompPreview() {
    TMDbTheme {
        Surface {
            val tvShowDetails = TvShowDetailsModel.dummyData()
            TvShowDetailsSeasonComp(
                preview = true,
                tvShowPosterPath = tvShowDetails.posterPath,
                seasons = tvShowDetails.seasons,
                onSeeAllPressed = {},
                onSeasonItemPressed = {}
            )
        }
    }
}