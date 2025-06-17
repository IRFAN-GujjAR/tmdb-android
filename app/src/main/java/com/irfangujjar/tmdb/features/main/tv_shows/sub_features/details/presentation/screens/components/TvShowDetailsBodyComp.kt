package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.details.DetailsBackdropComp
import com.irfangujjar.tmdb.core.ui.components.details.DetailsCastCrewItemsComp
import com.irfangujjar.tmdb.core.ui.components.details.YoutubeVideosComp
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.domain.models.TvShowDetailsModel


@Composable
fun TvShowDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    tvId: Int,
    tvShowDetails: TvShowDetailsModel,
    onNavigateToSeeAllTvShows: (TvShowsCategory) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onCastCrewSeeAllClick: (CreditsModel) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = outerPadding,
                    innerPaddingValues = innerPadding,
                    includeStartPadding = false,
                    includeEndPadding = false,
                    includeTopPadding = false
                )
            )
    ) {
        DetailsBackdropComp(
            preview = preview,
            type = MediaType.TvShow,
            backdropDetailsPath = tvShowDetails.backdropPath,
            backdropPath = tvShowDetails.backdropPath,
            posterDetailsPath = tvShowDetails.posterPath,
            posterPath = tvShowDetails.posterPath,
            title = tvShowDetails.name,
            voteAverage = tvShowDetails.voteAverage,
            voteCount = tvShowDetails.voteCount,
            genres = tvShowDetails.genres,
            overview = tvShowDetails.overview,
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (tvShowDetails.seasons.isNotEmpty())
            TvShowDetailsSeasonComp(
                preview = preview,
                tvId = tvId,
                tvShowName = tvShowDetails.name,
                tvShowPosterPath = tvShowDetails.posterPath,
                episodeImagePlaceHolder = tvShowDetails.backdropPath,
                seasons = tvShowDetails.seasons,
                onSeeAllPressed = {

                }
            )
        if (tvShowDetails.isCastCrewPresent())
            DetailsCastCrewItemsComp(
                preview = preview,
                credits = tvShowDetails.credits!!,
                onSeeAllClicked = {
                    onCastCrewSeeAllClick(tvShowDetails.credits)
                },
                onItemTapped = { id, name ->
                    onNavigateToCelebDetails(
                        HomeNavKey.CelebDetailsNavKey(
                            celebId = id,
                            name = name
                        )
                    )
                }
            )
        if (tvShowDetails.isVideosPresent())
            YoutubeVideosComp(
                preview = preview,
                videos = tvShowDetails.videos!!
            )
        if (tvShowDetails.isRecommendationsPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromTvShows(
                    tvShows = tvShowDetails.recommendedTvShows!!.tvShows,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.tvConfig(
                        category = TvShowsCategory.DetailsRecommended
                    )
                ),
                title = TvShowsCategory.DetailsRecommended.title,
                onSeeAllClick = { onNavigateToSeeAllTvShows(TvShowsCategory.DetailsRecommended) },
                onItemTapped = { id, title, posterPath, backdropPath ->
                    onNavigateToTvShowDetails(
                        HomeNavKey.TvShowDetailsNavKey(
                            tvId = id,
                            name = title,
                            posterPath = posterPath,
                            backdropPath = backdropPath
                        )
                    )
                }
            )
        }
        if (tvShowDetails.isSimilarPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromTvShows(
                    tvShows = tvShowDetails.similarTvShows!!.tvShows,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.tvConfig(
                        category = TvShowsCategory.DetailsSimilar
                    )
                ),
                title = TvShowsCategory.DetailsSimilar.title,
                onSeeAllClick = { onNavigateToSeeAllTvShows(TvShowsCategory.DetailsSimilar) },
                onItemTapped = { id, title, posterPath, backdropPath ->
                    onNavigateToTvShowDetails(
                        HomeNavKey.TvShowDetailsNavKey(
                            tvId = id,
                            name = title,
                            posterPath = posterPath,
                            backdropPath = backdropPath
                        )
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun TvShowDetailsBodyCompPreview() {
    TMDbTheme {
        Surface {
            TvShowDetailsBodyComp(
                preview = true,
                outerPadding = PaddingValues(0.dp),
                innerPadding = PaddingValues(0.dp),
                tvId = TvShowModel.dummyData().id,
                tvShowDetails = TvShowDetailsModel.dummyData(),
                onNavigateToSeeAllTvShows = {

                },
                onNavigateToTvShowDetails = {

                },
                onCastCrewSeeAllClick = {

                },
                onNavigateToCelebDetails = {}
            )
        }
    }

}