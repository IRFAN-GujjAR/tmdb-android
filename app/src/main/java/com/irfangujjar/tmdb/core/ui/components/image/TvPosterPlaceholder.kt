package com.irfangujjar.tmdb.core.ui.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun TvPosterPlaceholder(size: Dp) {
    Icon(
        imageVector = Icons.Default.Tv,
        contentDescription = "TV Placeholder",
        modifier = Modifier.size(size)
    )
}