package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import ThemeScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusViewModel
import com.irfangujjar.tmdb.features.about.presentation.screens.AboutScreen
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.CastCrewScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.screens.MovieCreditsScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.credits.presentation.screens.TvShowCreditsScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.screens.CelebDetailsScreen
import com.irfangujjar.tmdb.features.main.celebs.sub_features.see_all.presentation.screens.SeeAllCelebsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens.CollectionDetailsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.MovieDetailsScreen
import com.irfangujjar.tmdb.features.main.movies.sub_features.see_all.presentation.screens.SeeAllMoviesScreen
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens.TMDBMediaListScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.details.presentation.screens.TvShowDetailsScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens.SeasonDetailsScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens.SeeAllSeasonsScreen
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.see_all.presentation.screens.SeeAllTvShowsScreen
import com.irfangujjar.tmdb.features.media_action.presentation.screens.RateMediaScreen
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.MediaStateChangeListenerViewModel

@Composable
fun EntryProviderBuilder<NavKey>.HomeScreenEntries(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackBarHostState: SnackbarHostState,
    onNavigateToSeeAllMovies: (HomeNavKey.SeeAllMoviesNavKey) -> Unit,
    onNavigateToSeeAllTvShows: (HomeNavKey.SeeAllTvShowsNavKey) -> Unit,
    onNavigateToSeeAllCelebs: (HomeNavKey.SeeAllCelebsNavKey) -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit,
    onNavigateToCollectionDetails: (HomeNavKey.CollectionDetailsNavKey) -> Unit,
    onNavigateToSeeAllSeasons: (HomeNavKey.SeeAllSeasonsNavKey) -> Unit,
    onNavigateToSeasonDetails: (HomeNavKey.SeasonDetailsNavKey) -> Unit,
    onNavigateToCastCrew: (HomeNavKey.CastCrewNavKey) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToTheme: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToTMDBMediaList: (HomeNavKey.TMDBMediaListNavKey) -> Unit,
    onNavigateToMovieCredits: (HomeNavKey.MovieCreditsNavKey) -> Unit,
    onNavigateToTvShowCredits: (HomeNavKey.TvShowCreditsNavKey) -> Unit,
    onNavigateToRateMedia: (HomeNavKey.RateMediaNavKey) -> Unit,
    onBackPressed: () -> Unit,
    signInStatusViewModel: SignInStatusViewModel = hiltViewModel(),
    mediaStateChangeListenerViewModel: MediaStateChangeListenerViewModel = hiltViewModel()
) {
    BottomNavEntries(
        outerPadding = outerPadding,
        userTheme = userTheme,
        snackBarHostState = snackBarHostState,
        onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
        onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
        onNavigateToSeeCelebs = onNavigateToSeeAllCelebs,
        onNavigateToLogin = onNavigateToLogin,
        onNavigateToMovieDetails = onNavigateToMovieDetails,
        onNavigateToTvShowDetails = onNavigateToTvShowDetails,
        onNavigateToCelebDetails = onNavigateToCelebDetails,
        onNavigateToTheme = onNavigateToTheme,
        onNavigateToAbout = onNavigateToAbout,
        onNavigateToTMDBMediaList = onNavigateToTMDBMediaList,
        viewModel = signInStatusViewModel
    )
    entry<HomeNavKey.SeeAllMoviesNavKey> {
        SeeAllMoviesScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }
    entry<HomeNavKey.SeeAllTvShowsNavKey> {
        SeeAllTvShowsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }

    entry<HomeNavKey.SeeAllCelebsNavKey> {
        SeeAllCelebsScreen(
            outerPadding = outerPadding,
            snackbarHostState = snackBarHostState,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToCelebDetails = onNavigateToCelebDetails
        )
    }

    entry<HomeNavKey.MovieDetailsNavKey> {
        MovieDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            snackBarHostState = snackBarHostState,
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onNavigateToCastCrewDetails = onNavigateToCastCrew,
            onNavigateToCollectionDetails = onNavigateToCollectionDetails,
            onNavigateToCelebDetails = onNavigateToCelebDetails,
            signInStatusViewModel = signInStatusViewModel,
            onNavigateToRateMedia = onNavigateToRateMedia,
            mediaStateChangeListenerViewModel = mediaStateChangeListenerViewModel
        )
    }

    entry<HomeNavKey.TvShowDetailsNavKey> {
        TvShowDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            snackBarHostState = snackBarHostState,
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails,
            onNavigateToCastCrew = onNavigateToCastCrew,
            onNavigateToCelebDetails = onNavigateToCelebDetails,
            onNavigateToSeeAllSeasons = onNavigateToSeeAllSeasons,
            onNavigateToSeasonDetails = onNavigateToSeasonDetails,
            signInStatusViewModel = signInStatusViewModel,
            onNavigateToRateMedia = onNavigateToRateMedia,
            mediaStateChangeListenerViewModel = mediaStateChangeListenerViewModel
        )
    }

    entry<HomeNavKey.RateMediaNavKey> {
        RateMediaScreen(
            outerPadding = outerPadding,
            onBackStackPressed = onBackPressed,
            key = it,
            onRatingUpdated = { mediaStateType, mediaId, rating, isRated ->
                mediaStateChangeListenerViewModel.updateState(
                    mediaStateType = mediaStateType,
                    mediaId = mediaId,
                    rating = rating,
                    isRated = isRated
                )
                onBackPressed()
            }
        )
    }

    entry<HomeNavKey.CelebDetailsNavKey> {
        CelebDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails,
            onNavigateToMovieCredits = onNavigateToMovieCredits,
            onNavigateToTvShowCredits = onNavigateToTvShowCredits
        )
    }

    entry<HomeNavKey.MovieCreditsNavKey> {
        MovieCreditsScreen(
            outerPadding = outerPadding,
            onBackPressed = onBackPressed,
            key = it,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }

    entry<HomeNavKey.TvShowCreditsNavKey> {
        TvShowCreditsScreen(
            outerPadding = outerPadding,
            onBackPressed = onBackPressed,
            key = it,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }


    entry<HomeNavKey.CollectionDetailsNavKey> {
        CollectionDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            onBackPressed = onBackPressed,
            key = it,
            onNavigateToMovieDetails = onNavigateToMovieDetails
        )
    }

    entry<HomeNavKey.SeeAllSeasonsNavKey> {
        SeeAllSeasonsScreen(
            outerPadding = outerPadding,
            onBackPressed = onBackPressed,
            key = it,
            onNavigateToSeasonDetails = onNavigateToSeasonDetails
        )
    }

    entry<HomeNavKey.SeasonDetailsNavKey> {
        SeasonDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            onBackPressed = onBackPressed,
            key = it,
        )
    }

    entry<HomeNavKey.CastCrewNavKey> {
        CastCrewScreen(
            outerPadding = outerPadding,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToCelebDetails = onNavigateToCelebDetails
        )
    }

    entry<HomeNavKey.LoginNavKey> {
        LoginScreen(
            showBackStack = true,
            onBackStackPressed = onBackPressed,
            navigateToMainScreen = {
                signInStatusViewModel.changeStatusToSingedIn()
                onBackPressed()
            },
        )
    }

    entry<HomeNavKey.AboutNavKey> {
        AboutScreen(
            outerPadding = outerPadding,
            onBackStackPressed = onBackPressed
        )
    }

    entry<HomeNavKey.TMDBMediaListNavKey> {
        TMDBMediaListScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            onBackPressed = onBackPressed,
            key = it,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails
        )
    }

    entry<HomeNavKey.ThemeNavKey> {
        ThemeScreen(
            userTheme = userTheme,
            outerPadding = outerPadding,
            onBackStackPressed = onBackPressed,
        )
    }
}