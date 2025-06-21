package com.irfangujjar.tmdb.core.urls

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import com.irfangujjar.tmdb.core.ui.util.ThumbnailQuality

object URLS {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val SIGN_UP_URL = "https://www.themoviedb.org/signup"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val SHARE_BASE_URL = "https://www.themoviedb.org"

    fun getYoutubeThumbnail(videoId: String): String =
        "https://i.ytimg.com/vi/$videoId/${ThumbnailQuality.MEDIUM}"

    fun openYoutubeVideo(context: Context, videoId: String) {
        openUrl(context, "https://www.youtube.com/watch?v=$videoId".toUri())
    }

    fun openUrl(context: Context, url: Uri) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, url)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.d("URLS", "Error opening $url  $e")
        }
    }

    private fun convertTxtToShareableFormat(text: String): String =
        text.replace(" ", "-").lowercase()

    fun movieShareUrl(movieId: Int, title: String): Uri =
        "$SHARE_BASE_URL/movie/$movieId-${convertTxtToShareableFormat(title)}".toUri()

    fun tvShowShareUrl(tvId: Int, name: String): Uri =
        "$SHARE_BASE_URL/tv/$tvId-${convertTxtToShareableFormat(name)}".toUri()

    fun celebrityShareUrl(celebId: Int, name: String): Uri =
        "$SHARE_BASE_URL/person/$celebId-${convertTxtToShareableFormat(name)}".toUri()


}
