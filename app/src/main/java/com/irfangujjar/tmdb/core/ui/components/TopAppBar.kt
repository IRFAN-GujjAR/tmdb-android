package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    title: String? = null,
    actions: @Composable RowScope.() -> Unit = {},
    showBackStack: Boolean = false,
    onBackStackPressed: () -> Unit = {},
    matchSurfaceColor: Boolean = false
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = if (matchSurfaceColor) MaterialTheme.colorScheme.surface else
                MaterialTheme.colorScheme.background,
            titleContentColor = Color.White
        ),
        navigationIcon = {
            if (showBackStack)
                IconButton(onClick = onBackStackPressed) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "BackStack Arrow"
                    )
                }
        },
        actions = actions,
        title = {
            if (title != null)
                Text(title, maxLines = 1, overflow = TextOverflow.Ellipsis)
        }
    )
}