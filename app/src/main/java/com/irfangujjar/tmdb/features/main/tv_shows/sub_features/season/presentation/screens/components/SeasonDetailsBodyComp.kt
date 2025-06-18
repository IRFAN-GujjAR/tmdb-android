package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey.SeasonDetailsNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.CustomRating
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.StillSizes
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.EpisodeModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.domain.models.SeasonDetailsModel


@Composable
fun SeasonDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    key: SeasonDetailsNavKey,
    seasonDetails: SeasonDetailsModel
) {
    LazyColumn(
        contentPadding = ScreenPadding.getPadding(
            outerPaddingValues = outerPadding,
            innerPaddingValues = innerPadding,
            includeStartPadding = false,
            includeEndPadding = false
        )
    ) {
        item {
            HeaderComp(preview, seasonDetails, key)
        }
        if (!seasonDetails.overview.isNullOrBlank())
            item {
                Column {
                    CustomDivider(
                        topPadding = DividerTopPadding.OneAndHalf,
                        bottomPadding = DividerBottomPadding.OneAndHalf
                    )
                    AboutThisShowComp(overview = seasonDetails.overview)
                }
            }
        if (seasonDetails.episodes.isNotEmpty())
            item {
                Column {
                    CustomDivider(
                        topPadding = DividerTopPadding.OneAndHalf,
                        bottomPadding = DividerBottomPadding.OneAndHalf
                    )
                    TextRow("Episodes")
                }
            }
        if (seasonDetails.episodes.isNotEmpty())
            itemsIndexed(seasonDetails.episodes) { index, episode ->
                Column {
                    EpisodeComp(
                        preview = preview, episode = episode,
                        index = index,
                        imagePlaceHolder = key.episodeImagePlaceHolder
                    )
                    if (index < seasonDetails.episodes.size - 1)
                        CustomDivider(bottomPadding = DividerBottomPadding.OneAndHalf)
                }
            }
    }
}

@Composable
private fun HeaderComp(
    preview: Boolean,
    seasonDetails: SeasonDetailsModel,
    key: SeasonDetailsNavKey
) {
    Row(modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = MediaImageType.TvShow,
            imageUrl = seasonDetails.posterPath,
            width = 98.dp,
            height = 148.dp,
            contentScale = ContentScale.Crop,
            borderRadius = 0.dp,
            placeHolderSize = 60.dp,
            posterSize = PosterSizes.w185
        )
        Column {
            Text(
                key.tvShowName, fontSize = 18.sp, fontWeight = FontWeight.W500,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                seasonDetails.name, fontSize = 16.sp, color = Color.Gray, fontWeight =
                    FontWeight.W500,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp)
            )
            if (!seasonDetails.airDate.isNullOrBlank())
                Text(
                    seasonDetails.airDate,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.padding(top = 4.dp, start = 8.dp)
                )
            if (seasonDetails.voteAverage != null && seasonDetails.voteAverage != 0.0) {
                CustomRating(
                    voteAverage = seasonDetails.voteAverage,
                    voteCount = 0,
                    showVoteCount = false,
                    padding = PaddingValues(start = 6.dp, top = 8.dp)
                )
            }
        }
    }
}

@Composable
private fun AboutThisShowComp(overview: String) {
    Text(
        "About this show", fontSize = 16.sp, fontWeight = FontWeight.W500,
        modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())
    )
    Text(
        overview, fontSize = 13.sp, color = Color.Gray, modifier =
            Modifier.padding(
                start = ScreenPadding.getStartPadding(), end = ScreenPadding.getEndPadding(),
                top = 4.dp
            )
    )
}

@Composable
private fun EpisodeComp(
    preview: Boolean,
    index: Int,
    episode: EpisodeModel,
    imagePlaceHolder: String?
) {
    Row(
        modifier = Modifier.padding(
            start = ScreenPadding.getStartPadding(),
            end = ScreenPadding.getEndPadding(),
        )
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = true,
            type = MediaImageType.TvShow,
            imageUrl = episode.stillPath ?: imagePlaceHolder,
            width = 107.dp,
            height = 60.dp,
            contentScale = ContentScale.Crop,
            placeHolderSize = 36.dp,
            stillSize = StillSizes.w185
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                "${index + 1}. ${episode.name}",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
            Row {
                if (!episode.airDate.isNullOrBlank())
                    Text(
                        episode.airDate, fontSize = 12.sp, color = Color.White.copy(alpha = 0.7f),
                        fontWeight = FontWeight.W500
                    )
                if (episode.voteAverage != null && episode.voteCount != null) {
                    Spacer(modifier = Modifier.weight(1f))
                    CustomRating(
                        voteAverage = episode.voteAverage,
                        voteCount = episode.voteCount
                    )
                }
            }
            if (!episode.overview.isNullOrBlank())
                Text(
                    episode.overview, fontSize = 12.sp, fontWeight = FontWeight.W500,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 2.dp)
                )

        }
    }
}


@Preview
@Composable
private fun SeasonDetailsBodyCompPreview() {
    TMDbTheme {
        Surface {
            val tvShow = TvShowModel.dummyData()
            val tvShowDetails = TvShowDetailsModel.dummyData()
            SeasonDetailsBodyComp(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                key = SeasonDetailsNavKey(
                    tvId = tvShow.id,
                    tvShowName = tvShowDetails.name,
                    seasonName = tvShowDetails.seasons[2].name,
                    seasonNo = tvShowDetails.seasons[2].seasonNo,
                    tvShowPosterPath = tvShowDetails.posterPath,
                    episodeImagePlaceHolder = tvShowDetails.backdropPath
                ),
                seasonDetails = SeasonDetailsModel.dummyData()
            )
        }
    }
}

