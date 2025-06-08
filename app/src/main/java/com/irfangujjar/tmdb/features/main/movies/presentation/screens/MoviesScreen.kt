package com.irfangujjar.tmdb.features.main.movies.presentation.screens

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
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalTopRatedList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesModel
import com.irfangujjar.tmdb.features.main.movies.presentation.viewmodels.MoviesViewModel
import com.irfangujjar.tmdb.features.main.movies.presentation.viewmodels.state.MoviesState


@Composable
fun MoviesScreen(
    preview: Boolean = false, outerPadding: PaddingValues,
    viewModel: MoviesViewModel = hiltViewModel(),
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState?
) {

    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Movies"
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = viewModel.state.collectAsState().value
            when (state) {
                is MoviesState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.retry()
                    }
                )

                is MoviesState.ErrorWithCache -> {
                    val error = state.error
                    LaunchedEffect(Unit) {
                        snackbarHostState?.showSnackbar(error.message)
                    }
                    MoviesScreenBody(
                        preview = preview,
                        paddingValues = outerPadding,
                        innerPadding = innerPadding,
                        movies = state.movies,
                        isRefreshing = viewModel.isRefreshing,
                        onRefresh = { viewModel.refresh() }
                    )
                }

                is MoviesState.Loaded -> MoviesScreenBody(
                    preview = preview,
                    paddingValues = outerPadding,
                    innerPadding = innerPadding,
                    movies = state.movies,
                    isRefreshing = viewModel.isRefreshing,
                    onRefresh = { viewModel.refresh() }

                )

                MoviesState.Loading -> CustomLoading()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoviesScreenBody(
    preview: Boolean,
    paddingValues: PaddingValues,
    innerPadding: PaddingValues,
    movies: MoviesModel,
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
            MediaItemsHorizontalList(
                preview = preview,
                values = if (preview) MediaItemsHorizontalListValues.dummyDataMovie(
                    category = MoviesCategories.Popular,
                    isLandscape = false
                )
                else MediaItemsHorizontalListValues.fromMovies(
                    movies = movies.popular.movies,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                        MoviesCategories.Popular
                    ),
                ),
                title = MoviesCategories.Popular.name,
            ) {

            }
            CustomDivider(topPadding = DividerTopPadding.Double)
            MediaItemsHorizontalList(
                preview = preview,
                values = if (preview) MediaItemsHorizontalListValues.dummyDataMovie(
                    category = MoviesCategories.InTheatres,
                    isLandscape = true
                ) else MediaItemsHorizontalListValues.fromMovies(
                    movies = movies.inTheatres.movies,
                    isLandscape = true,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                        MoviesCategories.InTheatres
                    ),
                ),
                title = MoviesCategories.InTheatres.name
            ) {

            }
            CustomDivider(topPadding = DividerTopPadding.Double)
            MediaItemsHorizontalList(
                preview = preview,
                values = if (preview) MediaItemsHorizontalListValues.dummyDataMovie(
                    category = MoviesCategories.Trending,
                    isLandscape = false
                )
                else MediaItemsHorizontalListValues.fromMovies(
                    movies = movies.trending.movies,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                        MoviesCategories.Trending
                    ),
                ),
                title = MoviesCategories.Trending.name
            ) {

            }
            CustomDivider(topPadding = DividerTopPadding.Double)
            MediaItemsHorizontalTopRatedList(
                preview = preview,
                mediaType = MediaType.Movie,
                movies = if (preview) List(20) { MovieModel.dummyData() }
                else movies.topRated.movies,
                tvShows = null
            )
            CustomDivider(topPadding = DividerTopPadding.Double)
            MediaItemsHorizontalList(
                preview = preview,
                values = if (preview) MediaItemsHorizontalListValues.dummyDataMovie(
                    category = MoviesCategories.Upcoming,
                    isLandscape = false
                )
                else MediaItemsHorizontalListValues.fromMovies(
                    movies = movies.upcoming.movies,
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.movieConfig(MoviesCategories.Upcoming)
                ),
                title = MoviesCategories.Upcoming.name
            ) {

            }
        }
    }

}


@Preview
@Composable
private fun PreviewMovieScreen() {
    TMDbTheme {
        val emptyList = MoviesListModel(pageNo = 1, totalPages = 2, movies = emptyList())
        Surface {
            MoviesScreenBody(
                preview = true,
                paddingValues = PaddingValues(),
                innerPadding = PaddingValues(),
                movies = MoviesModel(
                    popular = emptyList,
                    inTheatres = emptyList,
                    trending = emptyList,
                    topRated = emptyList,
                    upcoming = emptyList
                ),
                isRefreshing = false
            ) { }
        }
    }
}