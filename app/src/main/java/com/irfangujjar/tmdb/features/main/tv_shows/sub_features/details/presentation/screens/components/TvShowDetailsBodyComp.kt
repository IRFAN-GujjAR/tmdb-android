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
import com.irfangujjar.tmdb.core.models.SeasonModel
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
import com.irfangujjar.tmdb.features.media_state.domain.models.MediaStateModel
import com.irfangujjar.tmdb.features.media_state.domain.models.RatedModel
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateState


@Composable
fun TvShowDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    tvShowDetails: TvShowDetailsModel,
    onNavigateToSeeAllTvShows: (TvShowsCategory) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onCastCrewSeeAllClick: (CreditsModel) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit,
    onSeeAllSeasonsTapped: (List<SeasonModel>) -> Unit,
    onSeasonItemPressed: (SeasonModel) -> Unit,
    mediaState: MediaStateState
) {
    val scrollState = rememberScrollState()
    var showUserRating = false
    var userRating = 0.0
    if (mediaState is MediaStateState.Loaded) {
        showUserRating = mediaState.mediaState.rated.value != 0.0
        if (showUserRating) {
            userRating = mediaState.mediaState.rated.value
        }
    }
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
            showUserRating = showUserRating,
            userRating = userRating
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (tvShowDetails.seasons.isNotEmpty())
            TvShowDetailsSeasonComp(
                preview = preview,
                tvShowPosterPath = tvShowDetails.posterPath,
                seasons = tvShowDetails.seasons,
                onSeeAllPressed = {
                    onSeeAllSeasonsTapped(tvShowDetails.seasons)
                },
                onSeasonItemPressed = onSeasonItemPressed
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
                bottomPadding = if (tvShowDetails.recommendedTvShows!!.tvShows.size > 4)
                    DividerBottomPadding.Half else DividerBottomPadding.OneAndHalf
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromTvShows(
                    tvShows = tvShowDetails.recommendedTvShows.tvShows,
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
                bottomPadding = if (tvShowDetails.similarTvShows!!.tvShows.size > 4)
                    DividerBottomPadding.Half else DividerBottomPadding.OneAndHalf
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromTvShows(
                    tvShows = tvShowDetails.similarTvShows.tvShows,
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
                tvShowDetails = TvShowDetailsModel.dummyData(),
                onNavigateToSeeAllTvShows = {

                },
                onNavigateToTvShowDetails = {

                },
                onCastCrewSeeAllClick = {

                },
                onNavigateToCelebDetails = {},
                onSeasonItemPressed = {},
                onSeeAllSeasonsTapped = {},
                mediaState = MediaStateState.Loaded(
                    mediaState = MediaStateModel(
                        id = TvShowModel.dummyData().id,
                        favorite = true,
                        rated = RatedModel(value = 8.6),
                        watchlist = true
                    )
                )
            )
        }
    }

}