package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.ShareIconButton
import com.irfangujjar.tmdb.core.ui.components.details.MovieTvDetailsAppBarActionsComp
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.core.urls.URLS
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusState
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.components.TvShowDetailsBodyComp
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.TvShowDetailsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.viewmodels.states.TvShowDetailsState
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.MediaStateChangeListenerViewModel
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.MediaStateViewModel


@Composable
fun TvShowDetailsScreen(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    key: HomeNavKey.TvShowDetailsNavKey,
    snackBarHostState: SnackbarHostState,
    onBackStackPressed: () -> Unit,
    viewModel: TvShowDetailsViewModel = hiltViewModel(),
    mediaStateViewModel: MediaStateViewModel = hiltViewModel(),
    onNavigateToSeeAllTvShows: (HomeNavKey.SeeAllTvShowsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onNavigateToCastCrew: (HomeNavKey.CastCrewNavKey) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit,
    onNavigateToSeeAllSeasons: (HomeNavKey.SeeAllSeasonsNavKey) -> Unit,
    onNavigateToSeasonDetails: (HomeNavKey.SeasonDetailsNavKey) -> Unit,
    onNavigateToRateMedia: (HomeNavKey.RateMediaNavKey) -> Unit,
    signInStatusViewModel: SignInStatusViewModel,
    mediaStateChangeListenerViewModel: MediaStateChangeListenerViewModel
) {

    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value
    val signInState = signInStatusViewModel.state.collectAsState().value
    val mediaState = mediaStateViewModel.state.collectAsState().value
    mediaStateViewModel.initialize(
        isLoggedIn = signInState is SignInStatusState.SignedIn,
        mediaId = key.tvId,
        type = MediaStateType.Tv,
        state = mediaStateChangeListenerViewModel.state,
        onResetMediaStateChanges = {
            mediaStateChangeListenerViewModel.resetState()
        }
    )
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.name,
                showBackStack = true,
                onBackStackPressed = onBackStackPressed,
                actions = {
                    MovieTvDetailsAppBarActionsComp(
                        snackBarHostState = snackBarHostState,
                        mediaId = key.tvId,
                        title = key.name,
                        posterPath = key.posterPath,
                        mediaStateType = MediaStateType.Tv,
                        state = mediaState,
                        signInState = signInState,
                        onReloadMediaState = {
                            mediaStateViewModel.load(
                                mediaId = key.tvId,
                                type = MediaStateType.Tv
                            )
                        },
                        onNavigateToRateMedia = onNavigateToRateMedia,
                    )
                    ShareIconButton(
                        url = URLS.tvShowShareUrl(
                            tvId = key.tvId,
                            name = key.name
                        )
                    )
                }
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                TvShowDetailsState.Loading -> CustomLoading()
                is TvShowDetailsState.Error -> CustomError(
                    error = state.error,
                    userTheme = userTheme,
                    onRetry = {
                        viewModel.loadTvShowDetails()
                    }
                )

                is TvShowDetailsState.Loaded -> TvShowDetailsBodyComp(
                    preview = false,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    tvShowDetails = state.tvShowDetails,
                    onNavigateToSeeAllTvShows = {
                        val argId = viewModel.saveSeeAllTvShowsArg(
                            if (it == TvShowsCategory.DetailsRecommended)
                                state.tvShowDetails.recommendedTvShows!! else
                                state.tvShowDetails.similarTvShows!!
                        )
                        onNavigateToSeeAllTvShows(
                            HomeNavKey.SeeAllTvShowsNavKey(
                                argId = argId,
                                tvId = key.tvId,
                                category = it
                            )
                        )
                    },
                    onNavigateToTvShowDetails = onNavigateToTvShowDetails,
                    onCastCrewSeeAllClick = {
                        val argId = viewModel.saveCastCrewArgs(it)
                        onNavigateToCastCrew(
                            HomeNavKey.CastCrewNavKey(
                                argId = argId
                            )
                        )
                    },
                    onNavigateToCelebDetails = onNavigateToCelebDetails,
                    onSeasonItemPressed = {
                        onNavigateToSeasonDetails(
                            HomeNavKey.SeasonDetailsNavKey(
                                tvId = key.tvId,
                                tvShowName = key.name,
                                seasonName = it.name,
                                seasonNo = it.seasonNo,
                                tvShowPosterPath = key.posterPath,
                                episodeImagePlaceHolder = key.backdropPath
                            )
                        )
                    },
                    onSeeAllSeasonsTapped = {
                        val argId = viewModel.saveSeeAllSeasons(it)
                        onNavigateToSeeAllSeasons(
                            HomeNavKey.SeeAllSeasonsNavKey(
                                argId = argId,
                                tvId = key.tvId,
                                tvShowName = key.name,
                                tvShowPosterPath = key.posterPath,
                                episodeImagePlaceHolder = key.backdropPath
                            )
                        )
                    },
                    mediaState = mediaState
                )
            }
        }
    }
}