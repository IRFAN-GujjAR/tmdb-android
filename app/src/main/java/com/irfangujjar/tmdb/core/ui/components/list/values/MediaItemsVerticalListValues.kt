package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class MediaItemsVerticalListValues(
    val mediaType: MediaType,
    val mediaIds: List<Int>,
    val mediaTitles: List<String>,
    val mediaGenres: List<List<Int>?>,
    val posterPaths: List<String?>,
    val backdropPaths: List<String?>,
    val voteAverages: List<Double?>,
    val voteCounts: List<Int?>
) {
    companion object {

        fun dummyData(mediaType: MediaType): MediaItemsVerticalListValues = if (mediaType.isMovie())
            fromMovies(movies = List(20) { MovieModel.dummyData() })
        else
            fromTvShows(tvShows = List(20) { TvShowModel.dummyData() })


        fun fromMovies(movies: List<MovieModel>): MediaItemsVerticalListValues {
            val mediaIds = mutableListOf<Int>()
            val mediaTitles = mutableListOf<String>()
            val mediaGenres = mutableListOf<List<Int>?>()
            val posterPaths = mutableListOf<String?>()
            val backdropPaths = mutableListOf<String?>()
            val voteAverages = mutableListOf<Double?>()
            val voteCounts = mutableListOf<Int?>()

            movies.forEach {
                mediaIds.add(it.id)
                mediaTitles.add(it.title)
                mediaGenres.add(it.genreIds)
                posterPaths.add(it.posterPath)
                backdropPaths.add(it.backdropPath)
                voteAverages.add(it.voteAverage)
                voteCounts.add(it.voteCount)
            }

            return MediaItemsVerticalListValues(
                mediaType = MediaType.Movie,
                mediaIds = mediaIds,
                mediaTitles = mediaTitles,
                mediaGenres = mediaGenres,
                posterPaths = posterPaths,
                backdropPaths = backdropPaths,
                voteAverages = voteAverages,
                voteCounts = voteCounts
            )
        }

        fun fromTvShows(tvShows: List<TvShowModel>): MediaItemsVerticalListValues {
            val mediaIds = mutableListOf<Int>()
            val mediaTitles = mutableListOf<String>()
            val mediaGenres = mutableListOf<List<Int>?>()
            val posterPaths = mutableListOf<String?>()
            val backdropPaths = mutableListOf<String?>()
            val voteAverages = mutableListOf<Double?>()
            val voteCounts = mutableListOf<Int?>()

            tvShows.forEach {
                mediaIds.add(it.id)
                mediaTitles.add(it.name)
                mediaGenres.add(it.genreIds)
                posterPaths.add(it.posterPath)
                backdropPaths.add(it.backdropPath)
                voteAverages.add(it.voteAverage)
                voteCounts.add(it.voteCount)
            }

            return MediaItemsVerticalListValues(
                mediaType = MediaType.TvShow,
                mediaIds = mediaIds,
                mediaTitles = mediaTitles,
                mediaGenres = mediaGenres,
                posterPaths = posterPaths,
                backdropPaths = backdropPaths,
                voteAverages = voteAverages,
                voteCounts = voteCounts
            )
        }
    }
}
