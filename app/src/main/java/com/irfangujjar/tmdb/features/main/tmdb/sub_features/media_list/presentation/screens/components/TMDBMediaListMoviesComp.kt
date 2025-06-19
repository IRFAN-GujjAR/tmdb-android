package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.movies.domain.models.MoviesListModel
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.TMDBMediaListMoviesViewModel

@Composable
fun TMDBMediaListMoviesComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    moviesList: MoviesListModel,
    category: TMDBMediaListCategory,
    sortBy: SortBy,
    listState: LazyListState? = null,
    viewModel: TMDBMediaListMoviesViewModel = hiltViewModel(),
    onNavigateToMovieDetails: (HomeNavKey.MovieDetailsNavKey) -> Unit,
) {
    viewModel.initializeValues(
        data = moviesList,
        category = category,
        sortBy = sortBy
    )

    val state = viewModel.state.collectAsState().value
    MediaItemsVerticalList(
        preview = preview,
        outerPadding = outerPadding,
        innerPadding = innerPadding,
        values = MediaItemsVerticalListValues.fromMovies(movies = state.movies),
        state = listState,
        onScrollThresholdReached = {
            viewModel.loadMore()
        },
        onItemTapped = { movieId, title, posterPath, backdropPath ->
            onNavigateToMovieDetails(
                HomeNavKey.MovieDetailsNavKey(
                    movieId = movieId,
                    title = title,
                    posterPath = posterPath,
                    backdropPath = backdropPath
                )
            )
        }
    )

}

