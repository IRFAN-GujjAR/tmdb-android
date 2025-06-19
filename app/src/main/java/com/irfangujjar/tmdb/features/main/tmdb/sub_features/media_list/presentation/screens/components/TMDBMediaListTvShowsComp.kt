package com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.SortBy
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.presentation.viewmodels.TMDBMediaListTvShowsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowsListModel

@Composable
fun TMDBMediaListTvShowsComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    tvShowsList: TvShowsListModel,
    category: TMDBMediaListCategory,
    sortBy: SortBy,
    listState: LazyListState? = null,
    viewModel: TMDBMediaListTvShowsViewModel = hiltViewModel(),
    onNavigateToTvShowDetails: (HomeNavKey.TvShowDetailsNavKey) -> Unit,
) {
    viewModel.initializeValues(
        data = tvShowsList,
        category = category,
        sortBy = sortBy
    )

    val state = viewModel.state.collectAsState().value

    MediaItemsVerticalList(
        preview = preview,
        outerPadding = outerPadding,
        innerPadding = innerPadding,
        values = MediaItemsVerticalListValues.fromTvShows(tvShows = state.tvShows),
        state = listState,
        onScrollThresholdReached = {
            viewModel.loadMore()
        },
        onItemTapped = { tvId, name, posterPath, backdropPath ->
            onNavigateToTvShowDetails(
                HomeNavKey.TvShowDetailsNavKey(
                    tvId = tvId,
                    name = name,
                    posterPath = posterPath,
                    backdropPath = backdropPath
                )
            )
        }
    )

}