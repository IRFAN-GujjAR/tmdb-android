package com.irfangujjar.tmdb.features.main.celebs.presentation.screens

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
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsModel
import com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components.PopularCelebs
import com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components.TrendingCelebs
import com.irfangujjar.tmdb.features.main.celebs.presentation.viewmodels.CelebsViewModel
import com.irfangujjar.tmdb.features.main.celebs.presentation.viewmodels.state.CelebsState


@Composable
fun CelebsScreen(
    preview: Boolean = false,
    paddingValues: PaddingValues,
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState?,
    viewModel: CelebsViewModel = hiltViewModel()
) {
    Scaffold(topBar = {
        CustomTopAppBar(
            title = "Celebrities"
        )
    }) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = viewModel.state.collectAsState().value
            when (state) {
                is CelebsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.retry()
                    }
                )

                is CelebsState.ErrorWithCache -> {
                    val error = state.error
                    LaunchedEffect(Unit) {
                        snackbarHostState?.showSnackbar(error.message)
                    }
                    CelebsScreenBody(
                        preview = preview,
                        paddingValues = paddingValues,
                        innerPadding = innerPadding,
                        celebs = state.celebs,
                        isRefreshing = viewModel.isRefreshing,
                        onRefresh = { viewModel.refresh() }
                    )
                }

                is CelebsState.Loaded -> CelebsScreenBody(
                    preview = preview,
                    paddingValues = paddingValues,
                    innerPadding = innerPadding,
                    celebs = state.celebs,
                    isRefreshing = viewModel.isRefreshing,
                    onRefresh = { viewModel.refresh() }

                )

                CelebsState.Loading -> CustomLoading()
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CelebsScreenBody(
    preview: Boolean,
    paddingValues: PaddingValues,
    innerPadding: PaddingValues,
    celebs: CelebsModel,
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
            PopularCelebs(preview = preview, popularCelebs = celebs.popular) {}
            CustomDivider()
            TrendingCelebs(preview = preview, celebs = celebs.trending.celebrities) { }
        }
    }
}

@Preview
@Composable
private fun CelebsScreenBodyPreview() {
    TMDbTheme {
        Surface {
            CelebsScreenBody(
                preview = true,
                paddingValues = PaddingValues(),
                innerPadding = PaddingValues(),
                celebs = CelebsModel.dummyData(),
                isRefreshing = false,
                onRefresh = {}
            )
        }
    }
}