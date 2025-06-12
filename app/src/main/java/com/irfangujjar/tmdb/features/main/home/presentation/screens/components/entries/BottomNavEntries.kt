package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.ui.util.CelebsCategory
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
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
    onNavigateToSeeAllMovies: (key: BottomNavKey, argId: String, category: MoviesCategory) -> Unit,
    onNavigateToSeeAllTvShows: (key: BottomNavKey, argId: String, category: TvShowsCategory) -> Unit,
    onNavigateToSeeCelebs: (key: BottomNavKey, argId: String, category: CelebsCategory) -> Unit,
    onNavigateToLogin: () -> Unit
) {
    BottomNavKey.items.forEach { bottomNavKey ->
        when (bottomNavKey) {
            BottomNavKey.MoviesNavKey -> entry<BottomNavKey.MoviesNavKey> {
                MoviesScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllMovies = { argId, category ->
                        onNavigateToSeeAllMovies(it, argId, category)
//                        moviesBackStack.add(
//                            HomeNavKey.SeeAllMoviesNavKey(
//                                argId = argId,
//                                category = category,
//                                movieId = null
//                            )
//                        )
                    }
                )
            }

            BottomNavKey.TVShowsNavKey -> entry<BottomNavKey.TVShowsNavKey> {
                TvShowsScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllTvShows = { argId, category ->
                        onNavigateToSeeAllTvShows(it, argId, category)
//                        tvShowsBackStack.add(
//                            HomeNavKey.SeeAllTvShowsNavKey(
//                                argId = argId,
//                                category = category,
//                                tvId = null
//                            )
//                        )

                    }
                )
            }

            BottomNavKey.CelebsNavKey -> entry<BottomNavKey.CelebsNavKey> {
                CelebsScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    onNavigateToSeeAllCelebs = { argId, category ->
                        onNavigateToSeeCelebs(it, argId, category)
//                        celebsBackStack.add(
//                            HomeNavKey.SeeAllCelebsNavKey(
//                                argId = argId,
//                                category = category
//                            )
//                        )
                    },
                )
            }

            BottomNavKey.SearchNavKey -> entry<BottomNavKey.SearchNavKey> {
                SearchScreen(
                    userTheme = userTheme,
                    outerPadding = outerPadding,
                    snackbarHostState = snackBarHostState,
                    onNavigateToDetailsPage = {

                    }
                )
            }

            BottomNavKey.TMDBNavKey -> entry<BottomNavKey.TMDBNavKey> {
                TMDBScreen(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackbarHostState = snackBarHostState,
                    navigateToLogin = {
                        onNavigateToLogin()
                    }
                ) { }
            }
        }
    }
}
