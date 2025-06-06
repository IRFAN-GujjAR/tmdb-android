package com.irfangujjar.tmdb.features.main.search.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchDetailsModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchDetailsState


@Composable
fun SearchDetails(
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    viewModel: SearchViewModel,
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

        is SearchDetailsState.Loaded -> SearchDetailsBody(
            userTheme = userTheme,
            outerPaddingValues = outerPaddingValues,
            innerPaddingValues = innerPaddingValues,
            searchDetails = state.searchDetails
        )
    }
}

@Composable
private fun SearchDetailsBody(
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    searchDetails: SearchDetailsModel
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = outerPaddingValues,
                    innerPaddingValues = innerPaddingValues
                )
            )
            .verticalScroll(scrollState)
    ) {
        Text("Loaded")
    }
}