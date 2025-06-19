package com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.MovieCreditsViewModel
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.viewmodels.states.MovieCreditsState
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.MovieCreditsModel


@Composable
fun MovieCreditsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    onBackPressed: () -> Unit,
    key: HomeNavKey.MovieCreditsNavKey,
    viewModel: MovieCreditsViewModel = hiltViewModel(),
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit
) {

    viewModel.initialize(key)
    
    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Movies", showBackStack = true,
                onBackStackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                MovieCreditsState.Initializing -> CustomLoading()
                is MovieCreditsState.Initialized -> MovieCreditsBody(
                    preview = preview,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    movieCredits = state.credits,
                    onMovieItemTapped = { movieId, title, posterPath, backdropPath ->
                        onNavigateToMovieDetails(
                            HomeNavKey.MovieDetailsNavKey(
                                movieId = movieId,
                                title = title,
                                posterPath = posterPath,
                                backdropPath = backdropPath
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun MovieCreditsBody(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    movieCredits: MovieCreditsModel,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit
) {
    if (movieCredits.isBothNotEmpty()) {

        var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
        val pagerState = rememberPagerState { 2 }

        val castListState = rememberLazyListState()
        val crewListState = rememberLazyListState()

        LaunchedEffect(selectedIndex) {
            pagerState.animateScrollToPage(selectedIndex)
        }

        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if (!pagerState.isScrollInProgress)
                selectedIndex = pagerState.currentPage
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
                    }, unselectedContentColor = Color.Gray,
                    text = { Text("Cast") })
                Tab(
                    selected = selectedIndex == 1, onClick = {
                        selectedIndex = 1
                    }, unselectedContentColor = Color.Gray,
                    text = { Text("Crew") })
            }
            HorizontalPager(state = pagerState) { index ->
                Box(modifier = Modifier.fillMaxSize()) {
                    MediaItemsVerticalList(
                        preview = preview,
                        outerPadding = PaddingValues(
                            top = ScreenPadding.getTopPadding(),
                            bottom = outerPadding.calculateBottomPadding()
                        ),
                        innerPadding = PaddingValues(
                            bottom = innerPadding.calculateBottomPadding()
                        ),
                        values = MediaItemsVerticalListValues.fromMovies(
                            movies = if (index == 0)
                                movieCredits.cast else movieCredits.crew
                        ),
                        state = if (index == 0) castListState else crewListState,
                        onItemTapped = onMovieItemTapped
                    )
                }
            }
        }

    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            MediaItemsVerticalList(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                values = MediaItemsVerticalListValues.fromMovies(
                    movies = if (movieCredits.isCastNotEmpty())
                        movieCredits.cast else movieCredits.crew
                ),
                onItemTapped = onMovieItemTapped
            )
        }
    }
}