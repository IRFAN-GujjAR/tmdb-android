package com.irfangujjar.tmdb.features.main.tv_shows.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontal
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalTopRated
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsModel
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.viewmodels.TvShowsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.viewstate.TvShowsState


@Composable
fun TvShowsScreen(
    preview: Boolean = false, paddingValues: PaddingValues,
    viewModel: TvShowsViewModel = hiltViewModel(),
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState?
) {

    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Tv Shows"
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = viewModel.state.collectAsState().value
            when (state) {
                is TvShowsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.retry()
                    }
                )

                is TvShowsState.ErrorWithCache -> {
                    val error = state.error
                    LaunchedEffect(Unit) {
                        snackbarHostState?.showSnackbar(error.message)
                    }
                    TvShowsScreenBody(
                        preview = preview,
                        paddingValues = paddingValues,
                        innerPadding = innerPadding,
                        tvShows = state.tvShows,
                        isRefreshing = viewModel.isRefreshing,
                        onRefresh = { viewModel.refresh() }
                    )
                }

                is TvShowsState.Loaded -> TvShowsScreenBody(
                    preview = preview,
                    paddingValues = paddingValues,
                    innerPadding = innerPadding,
                    tvShows = state.tvShows,
                    isRefreshing = viewModel.isRefreshing,
                    onRefresh = { viewModel.refresh() }

                )

                TvShowsState.Loading -> CustomLoading()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TvShowsScreenBody(
    preview: Boolean,
    paddingValues: PaddingValues,
    innerPadding: PaddingValues,
    tvShows: TvShowsModel,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    val scrollState = rememberScrollState()
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier
            .padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = paddingValues,
                    innerPaddingValues = innerPadding,
                    includeStartPadding = false,
                    includeEndPadding = false,
                    includeTopPadding = false,
                    includeBottomPadding = false
                )
            )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(
                    top = ScreenPadding.getTopPadding(),
                    bottom = ScreenPadding.getBottomPadding()
                )
        ) {
            MediaItemsHorizontal(
                preview = preview,
                values = if (preview) MediaItemsHorizontalValues.dummyDataTv(
                    category = TvShowsCategories.AiringToday,
                    isLandscape = true
                )
                else MediaItemsHorizontalValues.fromTvShows(
                    tvShows = tvShows.popular.tvShows,
                    isLandscape = true,
                    configValues = MediaItemsHorizontalConfigValues.tvConfig(
                        TvShowsCategories.AiringToday
                    ),
                ),
                title = TvShowsCategories.AiringToday.name,
            ) {

            }
            CustomDivider()
            MediaItemsHorizontal(
                preview = preview,
                values = if (preview) MediaItemsHorizontalValues.dummyDataTv(
                    category = TvShowsCategories.Trending,
                    isLandscape = false
                ) else MediaItemsHorizontalValues.fromTvShows(
                    tvShows = tvShows.trending.tvShows,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalConfigValues.tvConfig(
                        TvShowsCategories.Trending
                    ),
                ),
                title = TvShowsCategories.Trending.name
            ) {

            }
            CustomDivider()
            MediaItemsHorizontalTopRated(
                preview = preview,
                mediaType = MediaType.TvShow,
                tvShows = if (preview) List(20) { TvShowModel.dummyData() }
                else tvShows.topRated.tvShows,
                movies = null
            )
            CustomDivider()
            MediaItemsHorizontal(
                preview = preview,
                values = if (preview) MediaItemsHorizontalValues.dummyDataTv(
                    category = TvShowsCategories.Popular,
                    isLandscape = false
                )
                else MediaItemsHorizontalValues.fromTvShows(
                    tvShows = tvShows.popular.tvShows,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalConfigValues.tvConfig(TvShowsCategories.Popular)
                ),
                title = TvShowsCategories.Popular.name
            ) {

            }
        }
    }

}


@Preview
@Composable
private fun PreviewMovieScreen() {
    TMDbTheme {
        val emptyList = TvShowsListModel(pageNo = 1, totalPages = 2, tvShows = emptyList())
        Surface {
            TvShowsScreenBody(
                preview = true,
                paddingValues = PaddingValues(),
                innerPadding = PaddingValues(),
                tvShows = TvShowsModel(
                    airingToday = emptyList,
                    trending = emptyList,
                    topRated = emptyList,
                    popular = emptyList,
                ),
                isRefreshing = false
            ) { }
        }
    }
}