package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.irfangujjar.tmdb.core.urls.URLS


@Composable
fun ShareIconButton(url: String) {
    val context = LocalContext.current
    IconButton(onClick = {
        URLS.shareUrl(context = context, url = url)
    }) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share Icon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

