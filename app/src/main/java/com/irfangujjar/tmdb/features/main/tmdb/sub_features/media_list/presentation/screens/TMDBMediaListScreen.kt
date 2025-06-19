package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.domain.models.TMDBMediaListMoviesAndTvShowsModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens.components.TMDBMediaListMoviesComp
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens.components.TMDBMediaListTvShowsComp
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.TMDBMediaListViewModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.states.TMDBMediaListState
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel


@Composable
fun TMDBMediaListScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    onBackPressed: () -> Unit,
    key: HomeNavKey.TMDBMediaListNavKey,
    viewModel: TMDBMediaListViewModel = hiltViewModel(),
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit
) {

    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.category.categoryName, showBackStack = true,
                onBackStackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                TMDBMediaListState.Loading -> CustomLoading()
                is TMDBMediaListState.Error -> CustomError(
                    error = state.error, userTheme = userTheme,
                    onRetry = { viewModel.loadDetails() })

                is TMDBMediaListState.Empty -> {
                    val msg = when (key.category) {
                        TMDBMediaListCategory.Favorite -> "No Movie or Tv Show is added to favorites!"
                        TMDBMediaListCategory.Rated -> "No Movie or Tv Show is rated!"
                        TMDBMediaListCategory.Watchlist -> "No Movie or Tv Show is added to watchlist!"
                    }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            msg,
                            modifier = Modifier.padding(
                                ScreenPadding.getPadding(
                                    outerPaddingValues = outerPadding,
                                    innerPaddingValues = innerPadding
                                )
                            )
                        )
                    }
                }

                is TMDBMediaListState.Loaded -> MediaListBody(
                    preview = preview,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    mediaLists = state.mediaLists,
                    category = key.category,
                    sortBy = viewModel.sortBy.value,
                    onNavigateToMovieDetails = onNavigateToMovieDetails,
                    onNavigateToTvShowDetails = onNavigateToTvShowDetails
                )
            }
        }
    }
}


@Composable
private fun MediaListBody(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    mediaLists: TMDBMediaListMoviesAndTvShowsModel,
    category: TMDBMediaListCategory,
    sortBy: SortBy,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit
) {
    if (mediaLists.isBothNotEmpty()) {

        val moviesListState = rememberLazyListState()
        val tvShowsListState = rememberLazyListState()
        val pagerState = rememberPagerState { 2 }
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

        LaunchedEffect(selectedIndex) {
            pagerState.animateScrollToPage(selectedIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress) {
                selectedIndex = pagerState.currentPage
            }
        }

        Column(
            modifier = Modifier.padding(
                top = ScreenPadding.getTopPadding(
                    outerPaddingValues = outerPadding,
                    innerPaddingValues = innerPadding,
                    includeSpacing = false
                )
            )
        ) {
            TabRow(
                selectedTabIndex = selectedIndex,
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                Tab(
                    selected = selectedIndex == 0, onClick = {
                        selectedIndex = 0
                    },
                    text = { Text("Movies") },
                    unselectedContentColor = Color.Gray
                )
                Tab(
                    selected = selectedIndex == 1, onClick = {
                        selectedIndex = 1
                    }, text = { Text("Tv Shows") },
                    unselectedContentColor = Color.Gray
                )
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { index ->
                when (index) {
                    0 -> Box(modifier = Modifier.fillMaxSize()) {
                        TMDBMediaListMoviesComp(
                            preview = preview,
                            outerPadding = PaddingValues(
                                top = ScreenPadding.getTopPadding(),
                                bottom = outerPadding.calculateBottomPadding()
                            ),
                            innerPadding = PaddingValues(
                                bottom = innerPadding.calculateBottomPadding()
                            ),
                            moviesList = mediaLists.moviesList,
                            category = category,
                            sortBy = sortBy,
                            listState = moviesListState,
                            onNavigateToMovieDetails = onNavigateToMovieDetails
                        )
                    }

                    1 -> Box(modifier = Modifier.fillMaxSize()) {
                        TMDBMediaListTvShowsComp(
                            preview = preview,
                            outerPadding = PaddingValues(
                                top = ScreenPadding.getTopPadding(),
                                bottom = outerPadding.calculateBottomPadding()
                            ),
                            innerPadding = PaddingValues(
                                bottom = innerPadding.calculateBottomPadding()
                            ),
                            tvShowsList = mediaLists.tvShowsList,
                            category = category,
                            sortBy = sortBy,
                            listState = tvShowsListState,
                            onNavigateToTvShowDetails = onNavigateToTvShowDetails
                        )
                    }
                }
            }
        }
    } else {
        if (!mediaLists.isMoviesEmpty()) {
            TMDBMediaListMoviesComp(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                moviesList = mediaLists.moviesList,
                category = category,
                sortBy = sortBy,
                onNavigateToMovieDetails = onNavigateToMovieDetails
            )
        } else {
            TMDBMediaListTvShowsComp(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                tvShowsList = mediaLists.tvShowsList,
                category = category,
                sortBy = sortBy,
                onNavigateToTvShowDetails = onNavigateToTvShowDetails
            )
        }
    }
}

@Preview
@Composable
private fun MediaListBodyPreview() {
    TMDbTheme {
        Surface {
            MediaListBody(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                mediaLists = TMDBMediaListMoviesAndTvShowsModel(
                    moviesList = MoviesListModel.dummyData(),
                    tvShowsList = TvShowsListModel.dummyData()
                ),
                category = TMDBMediaListCategory.Favorite,
                sortBy = SortBy.DESC,
                onNavigateToMovieDetails = {},
                onNavigateToTvShowDetails = {}
            )
        }
    }
}
