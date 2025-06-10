package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class MediaItemHorizontalValues(
    val mediaType: MediaType,
    val mediaId: Int,
    val mediaTitle: String,
    val mediaGenre: List<Int>?,
    val posterPath: String?,
    val backdropPath: String?,
    val isLandscape: Boolean,
    val configValues: MediaItemsHorizontalListConfigValues,
) {
    companion object {
        fun fromListValues(
            listValues: MediaItemsHorizontalListValues,
            index: Int,
        ): MediaItemHorizontalValues = MediaItemHorizontalValues(
            mediaType = listValues.mediaType,
            mediaId = listValues.mediaIds[index],
            mediaTitle = listValues.mediaTitles[index],
            mediaGenre = listValues.mediaGenres[index],
            posterPath = listValues.posterPaths[index],
            backdropPath = listValues.backdropPaths[index],
            isLandscape = listValues.isLandscape,
            configValues = listValues.configValues,
        )

        fun dummyDataMovie(
            category: MoviesCategory,
            isLandscape: Boolean
        ): MediaItemHorizontalValues {
            val movie = MovieModel.dummyData()
            return MediaItemHorizontalValues(
                mediaType = MediaType.Movie,
                mediaId = movie.id,
                mediaTitle = movie.title,
                mediaGenre = movie.genreIds,
                posterPath = movie.posterPath,
                backdropPath = movie.backdropPath,
                isLandscape = isLandscape,
                configValues = MediaItemsHorizontalListConfigValues.movieConfig(
                    category
                ),
            )
        }

        fun dummyDataTv(
            category: TvShowsCategory,
            isLandscape: Boolean
        ): MediaItemHorizontalValues {
            val tvShow = TvShowModel.dummyData()
            return MediaItemHorizontalValues(
                mediaType = MediaType.Movie,
                mediaId = tvShow.id,
                mediaTitle = tvShow.name,
                mediaGenre = tvShow.genreIds,
                posterPath = tvShow.posterPath,
                backdropPath = tvShow.backdropPath,
                isLandscape = isLandscape,
                configValues = MediaItemsHorizontalListConfigValues.tvConfig(
                    category
                ),
            )
        }
    }
}