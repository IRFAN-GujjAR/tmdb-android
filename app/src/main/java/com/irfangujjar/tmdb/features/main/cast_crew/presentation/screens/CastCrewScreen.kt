package com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components.CastBodyComp
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components.CastCrewBodyWithTabsComp
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components.CreditType
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components.CrewBodyComp
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.viewmodels.CastCrewViewModel
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.viewmodels.states.CastCrewState


@Composable
fun CastCrewScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    key: HomeNavKey.CastCrewNavKey,
    viewModel: CastCrewViewModel = hiltViewModel(),
    onBackStackPressed: () -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit
) {

    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value
    
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Credits",
                showBackStack = true,
                onBackStackPressed = onBackStackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                CastCrewState.Initializing -> CustomLoading()
                is CastCrewState.Initialized -> {
                    val credits = state.credits

                    val type: CreditType =
                        if (credits.cast.isNotEmpty() && credits.crew.isNotEmpty())
                            CreditType.Both
                        else if (credits.cast.isNotEmpty())
                            CreditType.Cast
                        else
                            CreditType.Crew

                    val navigateToCelebDetails: (Int, String) -> Unit = { celebId, name ->
                        onNavigateToCelebDetails(
                            HomeNavKey.CelebDetailsNavKey(
                                celebId = celebId,
                                name = name
                            )
                        )
                    }
                    when (type) {
                        CreditType.Cast -> CastBodyComp(
                            preview = preview,
                            outerPadding = outerPadding,
                            innerPadding = innerPadding,
                            cast = credits.cast,
                            onCastItemTapped = navigateToCelebDetails,
                        )

                        CreditType.Crew -> CrewBodyComp(
                            preview = preview,
                            outerPadding = outerPadding,
                            innerPadding = innerPadding,
                            crew = credits.crew,
                            onCrewItemTapped = navigateToCelebDetails
                        )

                        CreditType.Both -> CastCrewBodyWithTabsComp(
                            preview = preview,
                            outerPadding = outerPadding,
                            innerPadding = innerPadding,
                            credits = credits,
                            onItemTapped = navigateToCelebDetails
                        )
                    }
                }
            }

        }
    }
}


@Preview
@Composable
private fun CastCrewScreenPreview() {
    TMDbTheme {
        CastCrewScreen(
            outerPadding = PaddingValues(),
            key = HomeNavKey.CastCrewNavKey(argId = "123"),
            onBackStackPressed = {},
            onNavigateToCelebDetails = {}
        )
    }
}