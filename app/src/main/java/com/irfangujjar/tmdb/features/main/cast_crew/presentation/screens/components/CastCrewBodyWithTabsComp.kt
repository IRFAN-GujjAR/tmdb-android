package com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType


@Composable
fun CastCrewBodyWithTabsComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    credits: CreditsModel,
    onItemTapped: (Int, String) -> Unit
) {

    val pagerState = rememberPagerState { 2 }
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(selectedIndex) {
        pagerState.animateScrollToPage(selectedIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress)
            selectedIndex = pagerState.currentPage
    }

    val listStates = rememberSaveable { List(2) { LazyListState() } }

    Column(
        modifier = Modifier.padding(
            top =
                ScreenPadding.getTopPadding(
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
                unselectedContentColor = Color.Gray,
                selected = selectedIndex == 0,
                text = { Text("Cast") },
                onClick = {
                    selectedIndex = 0
                }
            )
            Tab(
                selected = selectedIndex == 1,
                unselectedContentColor = Color.Gray,
                text = { Text("Crew") }, onClick = {
                    selectedIndex = 1
                })
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            if (index == 0)
                CastBodyComp(
                    preview = preview,
                    outerPadding = PaddingValues(
                        top = ScreenPadding.getTopPadding(), bottom =
                            outerPadding.calculateBottomPadding()
                    ),
                    innerPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding()),
                    listState = listStates[0],
                    cast = credits.cast,
                    onCastItemTapped = onItemTapped
                )
            else
                CrewBodyComp(
                    preview = preview,
                    outerPadding = PaddingValues(
                        top = ScreenPadding.getTopPadding(), bottom =
                            outerPadding.calculateBottomPadding()
                    ),
                    innerPadding = PaddingValues(bottom = innerPadding.calculateBottomPadding()),
                    listState = listStates[1],
                    crew = credits.crew,
                    onCrewItemTapped = onItemTapped
                )
        }
    }
}

@Preview
@Composable
private fun CastCrewBodyWithTabsCompPreview() {
    TMDbTheme {
        Surface {
            CastCrewBodyWithTabsComp(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                credits = CreditsModel.dummyData(type = MediaType.Movie),
                onItemTapped = { _, _ -> }
            )
        }
    }
}