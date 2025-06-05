package com.irfangujjar.tmdb.features.main.search.presentation.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.TrendingSearchState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingSearches(
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues, viewModel: SearchViewModel,
    snackbarHostState: SnackbarHostState?
) {
    val state = viewModel.trendingState.collectAsState().value

    when (state) {
        TrendingSearchState.Loading -> CustomLoading()
        is TrendingSearchState.Loaded -> TrendingSearchBody(
            outerPaddingValues = outerPaddingValues, innerPaddingValues = innerPaddingValues,
            trendingSearch = state.trendingSearch,
            isRefreshing = viewModel.isTrendingSearchRefreshing,
            onRefresh = { viewModel.refreshTrendingSearch() }
        )

        is TrendingSearchState.ErrorWithCache -> {
            val error = state.error
            LaunchedEffect(Unit) {
                snackbarHostState?.showSnackbar(error.message)
            }
            TrendingSearchBody(
                outerPaddingValues = outerPaddingValues, innerPaddingValues = innerPaddingValues,
                trendingSearch = state.trendingSearch,
                isRefreshing = viewModel.isTrendingSearchRefreshing,
                onRefresh = { viewModel.refreshTrendingSearch() }
            )
        }

        is TrendingSearchState.Error -> CustomError(
            error = state.error,
            userTheme = userTheme
        ) {
            viewModel.retryTrendingSearch()
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrendingSearchBody(
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    trendingSearch: SearchModel,
    isRefreshing: Boolean,
    onRefresh: () -> Unit
) {
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = onRefresh,
        modifier = Modifier.padding(
            ScreenPadding.getPadding(
                outerPaddingValues = outerPaddingValues,
                innerPaddingValues = innerPaddingValues,
                includeTopPadding = false,
                includeBottomPadding = false
            )
        )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                top = ScreenPadding.getTopPadding() * 2,
                bottom = ScreenPadding.getBottomPadding()
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    "Trending",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }
            items(8) {
                TextButton(onClick = {}) {
                    Text(
                        trendingSearch.searches[it].searchTitle,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
