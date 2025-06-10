package com.irfangujjar.tmdb.core.ui.components.list.values

import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.isMovie
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.models.TvShowModel

data class MediaItemVerticalValues(
    val mediaType: MediaType,
    val mediaId: Int,
    val mediaTitle: String,
    val mediaGenre: List<Int>?,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Double?,
    val voteCount: Int?
) {
    companion object {

        fun dummyData(mediaType: MediaType): MediaItemVerticalValues {
            return if (mediaType.isMovie()) {
                val movie = MovieModel.dummyData()
                MediaItemVerticalValues(
                    mediaType = MediaType.Movie,
                    mediaId = movie.id,
                    mediaTitle = movie.title,
                    mediaGenre = movie.genreIds,
                    posterPath = movie.posterPath,
                    backdropPath = movie.backdropPath,
                    voteAverage = movie.voteAverage,
                    voteCount = movie.voteCount
                )
            } else {
                val tvShow = TvShowModel.dummyData()
                MediaItemVerticalValues(
                    mediaType = MediaType.TvShow,
                    mediaId = tvShow.id,
                    mediaTitle = tvShow.name,
                    mediaGenre = tvShow.genreIds,
                    posterPath = tvShow.posterPath,
                    backdropPath = tvShow.backdropPath,
                    voteAverage = tvShow.voteAverage,
                    voteCount = tvShow.voteCount
                )
            }
        }

        fun fromListValues(
            values: MediaItemsVerticalListValues,
            index: Int
        ): MediaItemVerticalValues =
            MediaItemVerticalValues(
                mediaType = values.mediaType,
                mediaId = values.mediaIds[index],
                mediaTitle = values.mediaTitles[index],
                mediaGenre = values.mediaGenres[index],
                posterPath = values.posterPaths[index],
                backdropPath = values.backdropPaths[index],
                voteAverage = values.voteAverages[index],
                voteCount = values.voteCounts[index]
            )
    }
}

