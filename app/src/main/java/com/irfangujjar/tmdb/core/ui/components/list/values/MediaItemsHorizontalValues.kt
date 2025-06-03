package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class MediaItemsHorizontalValues(
    val mediaType: MediaType,
    val mediaIds: List<Int>,
    val mediaTitles: List<String>,
    val mediaGenres: List<List<Int>>,
    val posterPaths: List<String?>,
    val backdropPaths: List<String?>,
    val isLandscape: Boolean,
    val configValues: MediaItemsHorizontalConfigValues
) {
    companion object {
        fun fromMovies(
            movies: List<MovieModel>,
            isLandscape: Boolean,
            configValues: MediaItemsHorizontalConfigValues
        ): MediaItemsHorizontalValues = fromList(
            mediaType = MediaType.Movie,
            movies = movies,
            tvShows = null,
            isLandscape = isLandscape,
            configValues = configValues
        )

        fun fromTvShows(
            tvShows: List<TvShowModel>,
            isLandscape: Boolean,
            configValues: MediaItemsHorizontalConfigValues
        ): MediaItemsHorizontalValues = fromList(
            mediaType = MediaType.TvShow,
            movies = null,
            tvShows = tvShows,
            isLandscape = isLandscape,
            configValues = configValues
        )

        fun dummyDataMovie(
            category: MoviesCategories,
            isLandscape: Boolean,
        ): MediaItemsHorizontalValues {
            val movies = List(20) { MovieModel.dummyData() }
            return fromList(
                mediaType = MediaType.Movie,
                movies = movies,
                tvShows = null,
                isLandscape = isLandscape,
                configValues = MediaItemsHorizontalConfigValues.movieConfig(
                    category = category
                )
            )
        }

        fun dummyDataTv(
            category: TvShowsCategories,
            isLandscape: Boolean,
        ): MediaItemsHorizontalValues {
            val tvShows = List(20) { TvShowModel.dummyData() }
            return fromList(
                mediaType = MediaType.TvShow,
                movies = null,
                tvShows = tvShows,
                isLandscape = isLandscape,
                configValues = MediaItemsHorizontalConfigValues.tvConfig(
                    category = category
                )
            )
        }

        private fun fromList(
            mediaType: MediaType,
            movies: List<MovieModel>?,
            tvShows: List<TvShowModel>?,
            isLandscape: Boolean,
            configValues: MediaItemsHorizontalConfigValues
        ): MediaItemsHorizontalValues {
            val mediaIds = arrayListOf<Int>()
            val mediaTitles = arrayListOf<String>()
            val mediaGenres = arrayListOf<List<Int>>()
            val posterPaths = arrayListOf<String?>()
            val backdropPaths = arrayListOf<String?>()
            movies?.forEach {
                mediaIds.add(it.id)
                mediaTitles.add(it.title)
                mediaGenres.add(it.genreIds)
                posterPaths.add(it.posterPath)
                backdropPaths.add(it.backdropPath)
            }
            tvShows?.forEach {
                mediaIds.add(it.id)
                mediaTitles.add(it.name)
                mediaGenres.add(it.genreIds)
                posterPaths.add(it.posterPath)
                backdropPaths.add(it.backdropPath)
            }
            return MediaItemsHorizontalValues(
                mediaType = mediaType,
                mediaIds = mediaIds,
                mediaTitles = mediaTitles,
                mediaGenres = mediaGenres,
                posterPaths = posterPaths,
                backdropPaths = backdropPaths,
                isLandscape = isLandscape,
                configValues = configValues
            )
        }
    }
}

