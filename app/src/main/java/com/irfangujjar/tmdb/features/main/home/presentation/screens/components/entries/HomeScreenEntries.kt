package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import ThemeScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.about.presentation.screens.AboutScreen
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.CastCrewScreen
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
    onBackPressed: () -> Unit
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
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
            onNavigateToCastCrewDetails = onNavigateToCastCrew,
            onNavigateToCollectionDetails = onNavigateToCollectionDetails,
            onNavigateToCelebDetails = onNavigateToCelebDetails
        )
    }

    entry<HomeNavKey.TvShowDetailsNavKey> {
        TvShowDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
            onNavigateToTvShowDetails = onNavigateToTvShowDetails,
            onNavigateToCastCrew = onNavigateToCastCrew,
            onNavigateToCelebDetails = onNavigateToCelebDetails,
            onNavigateToSeeAllSeasons = onNavigateToSeeAllSeasons,
            onNavigateToSeasonDetails = onNavigateToSeasonDetails
        )
    }

    entry<HomeNavKey.CelebDetailsNavKey> {
        CelebDetailsScreen(
            outerPadding = outerPadding,
            userTheme = userTheme,
            key = it,
            onBackStackPressed = onBackPressed,
            onNavigateToMovieDetails = onNavigateToMovieDetails,
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
            navigateToMainScreen = onBackPressed
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