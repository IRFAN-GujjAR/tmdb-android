package com.irfangujjar.tmdb.features.main.search.presentation.screens.components.search_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.list.CelebItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemHorizontalConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

@Composable
fun SearchDetailsAll(
    preview: Boolean,
    moviesList: MoviesListModel,
    tvShowsList: TvShowsListModel,
    celebsList: CelebsListModel,
    topPadding: Dp,
    bottomPadding: Dp,
    onSeeAllMoviesClick: () -> Unit,
    onSeeAllTvShowsClick: () -> Unit,
    onSeeAllCelebsClick: () -> Unit,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit,
    onTvShowItemTapped: (Int, String, String?, String?) -> Unit,
    onCelebItemTapped: (Int, String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(
                top = topPadding,
                bottom = bottomPadding
            )
    ) {
        MediaItemsHorizontalList(
            preview = preview,
            values = MediaItemsHorizontalListValues.fromMovies(
                movies = moviesList.movies,
                isLandscape = false,
                configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                    MoviesCategory.Popular
                )
            ),
            title = "Movies",
            onSeeAllClick = onSeeAllMoviesClick,
            onItemTapped = onMovieItemTapped
        )
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = if (tvShowsList.tvShows.size > 4) DividerBottomPadding.Half
            else DividerBottomPadding.OneAndHalf
        )
        MediaItemsHorizontalList(
            preview = preview,
            values = MediaItemsHorizontalListValues.fromTvShows(
                tvShows = tvShowsList.tvShows,
                isLandscape = true,
                configValues = MediaItemsHorizontalListConfigValues.tvConfig(
                    TvShowsCategory.AiringToday
                )
            ),
            title = "Tv Shows",
            onSeeAllClick = onSeeAllTvShowsClick,
            onItemTapped = onTvShowItemTapped
        )
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = if (celebsList.celebrities.size > 4) DividerBottomPadding.Half
            else DividerBottomPadding.OneAndHalf
        )
        CelebItemsHorizontalList(
            preview = preview,
            values = CelebItemsHorizontalListValues.fromListValues(
                celebs = celebsList.celebrities,
                config = CelebItemHorizontalConfigValues.fromDefault()
            ),
            title = "Celebrities",
            onSeeAllClick = onSeeAllCelebsClick,
            onItemTapped = onCelebItemTapped
        )
    }
}

@Preview
@Composable
private fun SearchDetailsAllPreview() {
    TMDbTheme {
        Surface {
            SearchDetailsAll(
                preview = true,
                moviesList = MoviesListModel.dummyData(),
                tvShowsList = TvShowsListModel.dummyData(),
                celebsList = CelebsListModel.dummyData(),
                topPadding = 0.dp,
                bottomPadding = 0.dp,
                onSeeAllMoviesClick = {},
                onSeeAllTvShowsClick = {},
                onSeeAllCelebsClick = {},
                onMovieItemTapped = { _, _, _, _ -> },
                onTvShowItemTapped = { _, _, _, _ -> },
                onCelebItemTapped = { _, _ -> }
            )
        }
    }
}