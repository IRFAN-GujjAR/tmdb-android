package com.irfangujjar.tmdb.core.ui.util

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController

enum class MediaType(val title: String) {
    Movie("movie"),
    TvShow("tv");

    companion object {
        fun getFromString(value: String): MediaType {
            val movieEntry = entries.first()
            return if (movieEntry.title == value)
                Movie
            else TvShow
        }
    }
}

fun MediaType.isMovie() = this == MediaType.Movie
fun MediaType.imageType() = if (this.isMovie()) MediaImageType.Movie else MediaImageType.TvShow

enum class MoviesCategory(val title: String) {
    Popular("Popular"),
    InTheatres("Playing In Theatres"),
    Trending("Trending"),
    TopRated("Top Rated"),
    Upcoming("Upcoming"),
    DetailsRecommended("Recommended"),
    DetailsSimilar("Similar");
}


enum class TvShowsCategory(val title: String) {
    AiringToday("Airing Today"),
    Trending("Trending"),
    TopRated("Top Rated"),
    Popular("Popular"),
    DetailsRecommended("Recommended"),
    DetailsSimilar("Similar"),
}

enum class CelebsCategory(val title: String) {
    Popular("Popular"),
    Trending("Trending")
}

fun getMovieGenres(genres: List<Int>): String {
    val genreName = mapOf(
        28 to "Action",
        12 to "Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        14 to "Fantasy",
        36 to "History",
        27 to "Horror",
        10402 to "Music",
        9648 to "Mystery",
        10749 to "Romance",
        878 to "Science Fiction",
        10770 to "TV Movie",
        53 to "Thriller",
        10752 to "War",
        37 to "Western"
    )

    val result = genres.mapNotNull { genreName[it] }
    return if (result.isEmpty()) "" else result.joinToString(", ")
}

fun getTvShowsGenres(genres: List<Int>): String {
    val genreName = mapOf(
        10759 to "Action & Adventure",
        16 to "Animation",
        35 to "Comedy",
        80 to "Crime",
        99 to "Documentary",
        18 to "Drama",
        10751 to "Family",
        10762 to "Kids",
        9648 to "Mystery",
        10763 to "News",
        10764 to "Reality",
        10765 to "Sci-Fi & Fantasy",
        10766 to "Soap",
        10767 to "Talk",
        10768 to "War & Politics",
        37 to "Western"
    )

    val result = genres.mapNotNull { genreName[it] }
    return if (result.isEmpty()) "" else result.joinToString(", ")
}

fun hideKeyboardAndUnFocus(
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {
    keyboardController?.hide()
    focusManager.clearFocus()
}

data object ThumbnailQuality {
    /// 120*90
    const val DEFAULT = "default.jpg"

    /// 320*180
    const val MEDIUM = "mqdefault.jpg"

    /// 480*360
    const val HIGH = "hqdefault.jpg"

    /// 640*480
    const val STANDARD = "sddefault.jpg"

    /// Unscaled thumbnail
    const val MAX = "maxresdefault.jpg"
}
