package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.celebs.presentation.screens.CelebsScreen
import com.irfangujjar.tmdb.features.main.movies.presentation.screens.MoviesScreen
import com.irfangujjar.tmdb.features.main.search.presentation.screens.SearchScreen
import com.irfangujjar.tmdb.features.main.tmdb.presentation.screens.TMDBScreen
import com.irfangujjar.tmdb.features.main.tv_shows.presentation.screens.TvShowsScreen

@Composable
fun EntryProviderBuilder<NavKey>.BottomNavEntries(
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackBarHostState: SnackbarHostState,
    onNavigateToSeeAllMovies: (HomeNavKey.SeeAllMoviesNavKey) -> Unit,
    onNavigateToSeeAllTvShows: (HomeNavKey.SeeAllTvShowsNavKey) -> Unit,
    onNavigateToSeeCelebs: (HomeNavKey.SeeAllCelebsNavKey) -> Unit,
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
    onNavigateToCelebDetails: (HomeNavKey.CelebDetailsNavKey) -> Unit,
    onNavigateToLogin: () -> Unit,
    onNavigateToTheme: () -> Unit
) {
    BottomNavKey.items.forEach { bottomNavKey ->
        when (bottomNavKey) {
            BottomNavKey.MoviesNavKey -> entry<BottomNavKey.MoviesNavKey> {
                MoviesScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllMovies = onNavigateToSeeAllMovies,
                    onNavigateToMovieDetails = onNavigateToMovieDetails
                )
            }

            BottomNavKey.TVShowsNavKey -> entry<BottomNavKey.TVShowsNavKey> {
                TvShowsScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllTvShows = onNavigateToSeeAllTvShows,
                    onNavigateToTvShowDetails = onNavigateToTvShowDetails
                )
            }

            BottomNavKey.CelebsNavKey -> entry<BottomNavKey.CelebsNavKey> {
                CelebsScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllCelebs = onNavigateToSeeCelebs,
                    onNavigateToCelebDetails = onNavigateToCelebDetails
                )
            }

            BottomNavKey.SearchNavKey -> entry<BottomNavKey.SearchNavKey> {
                SearchScreen(
                    userTheme = userTheme,
                    outerPadding = outerPadding,
                    snackbarHostState = snackBarHostState,
                    onNavigateToMovieDetails = onNavigateToMovieDetails,
                    onNavigateToTvShowDetails = onNavigateToTvShowDetails,
                    onNavigateToCelebDetails = onNavigateToCelebDetails
                )
            }

            BottomNavKey.TMDBNavKey -> entry<BottomNavKey.TMDBNavKey> {
                TMDBScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToLogin = onNavigateToLogin,
                    onNavigateToTheme = onNavigateToTheme
                )
            }
        }
    }
}
