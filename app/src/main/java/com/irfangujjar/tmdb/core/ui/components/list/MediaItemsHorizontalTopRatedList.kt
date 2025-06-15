package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel


@Composable
fun MediaItemsHorizontalTopRatedList(
    preview: Boolean = false,
    mediaType: MediaType,
    movies: List<MovieModel>?,
    tvShows: List<TvShowModel>?,
    onSeeAllClick: () -> Unit,
    onItemTapped: (Int, String, String?, String?) -> Unit
) {
    Column {
        TextRow(title = "Top Rated", onSeeAllClick = onSeeAllClick)
        LazyRow(
            modifier = Modifier.padding(top = 4.dp),
            contentPadding = PaddingValues(
                horizontal = ScreenPadding.getHorizontalPadding()
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(4) { outerIndex ->
                Column(modifier = Modifier.width(300.dp)) {
                    repeat(5) { innerIndex ->
                        var itemIndex = innerIndex
                        if (outerIndex != 0) {
                            itemIndex = (outerIndex * 5) + innerIndex
                        }
                        var movie: MovieModel? = null
                        var tvShow: TvShowModel? = null

                        when (mediaType) {
                            MediaType.Movie -> {
                                movie = movies!![itemIndex]
                            }

                            MediaType.TvShow -> {
                                tvShow = tvShows!![itemIndex]
                            }
                        }
                        MediaItemHorizontalTopRated(
                            preview = preview,
                            type = mediaType,
                            movie = movie,
                            tvShow = tvShow,
                            onItemTapped = onItemTapped
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun MediaItemsHorizontalTopRatedPreview() {
    TMDbTheme {
        Surface {
            MediaItemsHorizontalTopRatedList(
                preview = true,
                mediaType = MediaType.Movie,
                movies = List(20) { MovieModel.dummyData() },
                tvShows = null,
                onSeeAllClick = {},
                onItemTapped = { _, _, _, _ -> }
            )
        }
    }
}