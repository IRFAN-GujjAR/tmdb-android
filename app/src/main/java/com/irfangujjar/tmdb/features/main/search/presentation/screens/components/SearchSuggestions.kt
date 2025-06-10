package com.irfangujjar.tmdb.features.main.search.presentation.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.hideKeyboardAndUnFocus
import com.irfangujjar.tmdb.features.main.search.domain.models.SearchItemModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.SearchViewModel
import com.irfangujjar.tmdb.features.main.search.presentation.viewmodels.state.SearchSuggestionState

@Composable
fun SearchSuggestions(
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    viewModel: SearchViewModel
) {
    val state = viewModel.suggestionsState.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    when (state) {
        SearchSuggestionState.Idle -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Search for movies,tv shows, celebrities",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400
            )
        }

        SearchSuggestionState.Loading -> CustomLoading()
        is SearchSuggestionState.Error -> CustomError(
            error = state.error,
            userTheme = userTheme
        ) {
            viewModel.retrySearchSuggestions()
        }

        is SearchSuggestionState.Loaded -> SearchSuggestionsBody(
            userTheme = userTheme,
            outerPaddingValues = outerPaddingValues,
            innerPaddingValues = innerPaddingValues,
            searches = state.searchSuggestion.searches,
            onSuggestionClicked = {
                hideKeyboardAndUnFocus(
                    keyboardController = keyboardController,
                    focusManager = focusManager
                )
                viewModel.onSuggestionItemSelected(it)
            }
        )
    }
}

@Composable
private fun SearchSuggestionsBody(
    userTheme: UserTheme,
    outerPaddingValues: PaddingValues,
    innerPaddingValues: PaddingValues,
    searches: List<SearchItemModel>,
    onSuggestionClicked: (String) -> Unit
) {
    if (searches.isEmpty())
        Box(
            modifier = Modifier.padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = outerPaddingValues,
                    innerPaddingValues = innerPaddingValues
                )
            ),
            contentAlignment = Alignment.Center
        ) {
            NoSearchItemsFound(userTheme = userTheme)
        }
    else
        LazyColumn(
            contentPadding = ScreenPadding.getPadding(
                outerPaddingValues = outerPaddingValues,
                innerPaddingValues = innerPaddingValues
            )
        ) {
            items(searches) {
                ListItem(
                    modifier = Modifier.clickable(onClick = {
                        onSuggestionClicked(it.searchTitle)
                    }),
                    headlineContent = { Text(it.searchTitle, color = Color.Gray) },
                    leadingContent = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                )
            }
        }
}
