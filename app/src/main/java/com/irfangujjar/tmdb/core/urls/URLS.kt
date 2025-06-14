package com.irfangujjar.tmdb.core.urls

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.net.toUri
import com.irfangujjar.tmdb.core.ui.util.ThumbnailQuality

object URLS {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val SIGN_UP_URL = "https://www.themoviedb.org/signup"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

    fun getYoutubeThumbnail(videoId: String): String =
        "https://i.ytimg.com/vi/$videoId/${ThumbnailQuality.MEDIUM}"

    fun openYoutubeVideo(context: Context, videoId: String) {
        try {
            val intent = Intent(
                Intent.ACTION_VIEW,
                "https://www.youtube.com/watch?v=$videoId".toUri()
            )
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.d(
                "URLS",
                "Error opening YouTube video: ${e.message}"
            )
        }
    }
}