package com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.list.CelebItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.viewmodels.SeeAllCelebsViewModel


@Composable
fun SeeAllCelebsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    key: HomeNavKey.SeeAllCelebsNavKey,
    viewModel: SeeAllCelebsViewModel = hiltViewModel(),
    onBackStackPressed: () -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit
) {

    val listState = rememberLazyListState()

    viewModel.initialize(key)

    val celebrities = viewModel.state.collectAsState().value.celebrities

    if (viewModel.showAlert) {
        LaunchedEffect(Unit) {
            snackbarHostState.showSnackbar(viewModel.alertMessage)
            viewModel.clearAlert()
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.category.name,
                showBackStack = true,
                onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            CelebItemsVerticalList(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                state = listState,
                values = CelebItemsVerticalListValues.fromCelebs(celebs = celebrities),
                onScrollThresholdReached = {
                    viewModel.loadMore()
                },
                onItemTapped = { celebId, name ->
                    onNavigateToCelebDetails(
                        HomeNavKey.CelebDetailsNavKey(
                            celebId = celebId,
                            name = name
                        )
                    )
                }
            )
        }
    }
}