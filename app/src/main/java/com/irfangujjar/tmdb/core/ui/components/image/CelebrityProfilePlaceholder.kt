package com.irfangujjar.tmdb.core.ui.components.image

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun CelebrityProfilePlaceholder(
    size: Dp,
    modifier: Modifier? = null
) {
    val modifier = modifier ?: Modifier
    Icon(
        imageVector = Icons.Default.Person,
        contentDescription = "Celebrity Profile Placeholder",
        modifier = modifier.size(size),
    )
}