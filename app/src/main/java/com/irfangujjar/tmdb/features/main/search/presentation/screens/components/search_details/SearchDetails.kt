package com.irfangujjar.tmdb.features.main.search.presentation.screens.components.search_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.presentation.screens.components.NoSearchItemsFound
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchDetailsState

@Composable
fun SearchDetails(
    preview: Boolean = false,
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    viewModel: SearchViewModel,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit,
    onTvShowItemTapped: (Int, String, String?, String?) -> Unit,
    onCelebItemTapped: (Int, String) -> Unit
) {

    val state = viewModel.detailsState.collectAsState().value
    when (state) {
        SearchDetailsState.Loading -> CustomLoading()
        is SearchDetailsState.Error -> CustomError(
            error = state.error,
            userTheme = userTheme
        ) {
            viewModel.retrySearchDetails()
        }

        is SearchDetailsState.Loaded -> if (state.searchDetails.isEmpty())
            NoSearchItemsFound(userTheme = userTheme)
        else SearchDetailsBody(
            preview = preview,
            outerPaddingValues = outerPaddingValues,
            innerPaddingValues = innerPaddingValues,
            searchDetails = state.searchDetails,
            query = viewModel.query,
            onMovieItemTapped = onMovieItemTapped,
            onTvShowItemTapped = onTvShowItemTapped,
            onCelebItemTapped = onCelebItemTapped
        )
    }
}


@Composable
private fun SearchDetailsBody(
    preview: Boolean,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    searchDetails: SearchDetailsModel,
    query: String,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit,
    onTvShowItemTapped: (Int, String, String?, String?) -> Unit,
    onCelebItemTapped: (Int, String) -> Unit
) {

    val bottomPadding = ScreenPadding.getBottomPadding(
        outerPaddingValues = outerPaddingValues,
        innerPaddingValues = innerPaddingValues
    )

    if (searchDetails.length() < 2) {
        val topPadding = ScreenPadding.getTopPadding(
            outerPaddingValues = outerPaddingValues,
            innerPaddingValues = innerPaddingValues
        )

        if (!searchDetails.isMoviesEmpty()) {
            SearchDetailsMovies(
                preview = preview,
                query = query,
                moviesList = searchDetails.moviesList,
                topPadding = topPadding,
                bottomPadding = bottomPadding,
                listState = null,
                onMovieItemTapped = onMovieItemTapped
            )
        } else if (!searchDetails.isTvShowsEmpty()) {
            SearchDetailsTvShows(
                preview = preview,
                topPadding = topPadding,
                bottomPadding = bottomPadding,
                listState = null,
                tvShowsList = searchDetails.tvShowsList,
                query = query,
                onTvShowItemTapped = onTvShowItemTapped
            )
        } else {
            SearchDetailsCelebs(
                preview = preview,
                topPadding = topPadding,
                bottomPadding = bottomPadding,
                listState = null,
                celebsList = searchDetails.celebsList,
                query = query,
                onCelebItemTapped = onCelebItemTapped
            )
        }
    } else {
        val topPadding = ScreenPadding.getTopPadding()

        val tabItems = getTabItems(searchDetails = searchDetails)
        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
        val pagerState = rememberPagerState { tabItems.size }
        val listStateSize = if (searchDetails.isAllPresent()) tabItems.size - 1 else tabItems.size
        val listStates = List(listStateSize) { rememberLazyListState() }

        LaunchedEffect(selectedIndex) {
            pagerState.animateScrollToPage(selectedIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress)
                selectedIndex = pagerState.currentPage
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = ScreenPadding.getTopPadding(
                        outerPaddingValues = outerPaddingValues,
                        innerPaddingValues = innerPaddingValues,
                        includeSpacing = false
                    )
                )
        ) {
            TabRow(
                selectedTabIndex = selectedIndex,
                containerColor = MaterialTheme.colorScheme.surfaceContainer
            ) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        unselectedContentColor = Color.Gray,
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                        },
                        text = { Text(item.title) },
                    )
                }
            }



            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                userScrollEnabled = !searchDetails.isAllPresent()
            ) { index ->
                HorizontalPagerBody(
                    searchDetails = searchDetails,
                    index = index,
                    preview = preview,
                    topPadding = topPadding,
                    bottomPadding = bottomPadding,
                    listStates = listStates,
                    query = query,
                    onSeeAllMoviesClick = {
                        selectedIndex = 1
                    },
                    onSeeAllTvShowsClick = {
                        selectedIndex = 2
                    },
                    onSeeAllCelebsClick = {
                        selectedIndex = 3
                    },
                    onMovieItemTapped = onMovieItemTapped,
                    onTvShowItemTapped = onTvShowItemTapped,
                    onCelebItemTapped = onCelebItemTapped
                )
            }
        }
    }
}

@Composable
private fun HorizontalPagerBody(
    searchDetails: SearchDetailsModel,
    index: Int,
    preview: Boolean,
    topPadding: Dp,
    bottomPadding: Dp,
    query: String,
    listStates: List<LazyListState>,
    onSeeAllMoviesClick: () -> Unit,
    onSeeAllTvShowsClick: () -> Unit,
    onSeeAllCelebsClick: () -> Unit,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit,
    onTvShowItemTapped: (Int, String, String?, String?) -> Unit,
    onCelebItemTapped: (Int, String) -> Unit
) {
    if (searchDetails.isAllPresent()) {
        when (index) {
            0 -> SearchDetailsAll(
                preview = preview, moviesList = searchDetails.moviesList,
                tvShowsList = searchDetails.tvShowsList,
                celebsList = searchDetails.celebsList,
                topPadding = topPadding,
                bottomPadding = bottomPadding,
                onSeeAllMoviesClick = onSeeAllMoviesClick,
                onSeeAllTvShowsClick = onSeeAllTvShowsClick,
                onSeeAllCelebsClick = onSeeAllCelebsClick,
                onMovieItemTapped = onMovieItemTapped,
                onTvShowItemTapped = onTvShowItemTapped,
                onCelebItemTapped = onCelebItemTapped
            )

            1 -> SearchDetailsMovies(
                preview = preview,
                moviesList = searchDetails.moviesList,
                bottomPadding = bottomPadding,
                query = query,
                listState = listStates[0],
                onMovieItemTapped = onMovieItemTapped
            )

            2 -> SearchDetailsTvShows(
                preview = preview,
                bottomPadding = bottomPadding,
                listState = listStates[1],
                tvShowsList = searchDetails.tvShowsList,
                query = query,
                onTvShowItemTapped = onTvShowItemTapped
            )

            3 -> SearchDetailsCelebs(
                preview = preview,
                bottomPadding = bottomPadding,
                listState = listStates[2],
                celebsList = searchDetails.celebsList,
                query = query,
                onCelebItemTapped = onCelebItemTapped
            )
        }
    } else {
        when (index) {
            0 -> {
                if (!searchDetails.isMoviesEmpty())
                    SearchDetailsMovies(
                        preview = preview,
                        moviesList = searchDetails.moviesList,
                        bottomPadding = bottomPadding,
                        query = query,
                        listState = listStates[0],
                        onMovieItemTapped = onMovieItemTapped
                    )
                else if (!searchDetails.isTvShowsEmpty())
                    SearchDetailsTvShows(
                        preview = preview,
                        bottomPadding = bottomPadding,
                        listState = listStates[0],
                        tvShowsList = searchDetails.tvShowsList,
                        query = query,
                        onTvShowItemTapped = onTvShowItemTapped
                    )
                else
                    SearchDetailsCelebs(
                        preview = preview,
                        listState = listStates[0],
                        bottomPadding = bottomPadding,
                        celebsList = searchDetails.celebsList,
                        query = query,
                        onCelebItemTapped = onCelebItemTapped
                    )
            }

            1 -> {
                if (!searchDetails.isTvShowsEmpty())
                    SearchDetailsTvShows(
                        preview = preview,
                        bottomPadding = bottomPadding,
                        listState = listStates[1],
                        tvShowsList = searchDetails.tvShowsList,
                        query = query,
                        onTvShowItemTapped = onTvShowItemTapped
                    )
                else
                    SearchDetailsCelebs(
                        preview = preview,
                        bottomPadding = bottomPadding,
                        listState = listStates[1],
                        celebsList = searchDetails.celebsList,
                        query = query,
                        onCelebItemTapped = onCelebItemTapped
                    )
            }
        }
    }
}

internal data class TabItem(
    val title: String,
)


private fun getTabItems(searchDetails: SearchDetailsModel): List<TabItem> {
    if (searchDetails.isAllPresent()) {
        return listOf(
            TabItem(title = "All"), TabItem(title = "Movies"),
            TabItem(title = "Tv Shows"), TabItem(title = "Celebrities")
        )
    } else {
        val tabItems = mutableListOf<TabItem>()
        if (!searchDetails.isMoviesEmpty()) {
            tabItems.add(TabItem(title = "Movies"))
        }
        if (!searchDetails.isTvShowsEmpty()) {
            tabItems.add(TabItem(title = "Movies"))
        }
        if (!searchDetails.isCelebsEmpty()) {
            tabItems.add(TabItem(title = "Celebrities"))
        }
        return tabItems
    }
}

