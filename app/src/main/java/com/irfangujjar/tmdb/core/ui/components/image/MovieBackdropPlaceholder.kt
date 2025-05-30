package com.irfangujjar.tmdb.core.ui.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun MovieBackdropPlaceholder(size: Dp) {
    Icon(
        imageVector = Icons.Default.Movie,
        contentDescription = "Movie Backdrop Placeholder",
        modifier = Modifier.size(size)
    )
}