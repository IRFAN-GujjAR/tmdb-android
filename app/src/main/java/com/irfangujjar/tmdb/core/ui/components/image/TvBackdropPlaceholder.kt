package com.irfangujjar.tmdb.core.ui.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun TvBackdropPlaceholder(size: Dp) {
    Icon(
        imageVector = Icons.Default.LiveTv,
        contentDescription = "Tv Backdrop Placeholder",
        modifier = Modifier.size(size)
    )
}