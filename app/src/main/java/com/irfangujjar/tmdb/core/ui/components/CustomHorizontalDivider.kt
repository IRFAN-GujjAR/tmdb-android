package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding


@Composable
fun CustomDivider(
    topPadding: Dp = 24.dp,
    startPadding: Dp = ScreenPadding.getStartPadding(),
    endPadding: Dp = 0.dp,
    bottomPadding: Dp = 12.dp
) {
    HorizontalDivider(
        modifier = Modifier.padding(
            start = startPadding,
            end = endPadding,
            top = topPadding,
            bottom = bottomPadding
        ),
        thickness = 0.5.dp
    )
}