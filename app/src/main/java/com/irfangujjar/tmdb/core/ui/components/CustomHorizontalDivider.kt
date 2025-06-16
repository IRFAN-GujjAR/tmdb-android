package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding


@Composable
fun CustomDivider(
    topPadding: DividerTopPadding = DividerTopPadding.Default,
    startPadding: DividerStartPadding = DividerStartPadding.Default,
    endPadding: DividerEndPadding = DividerEndPadding.Zero,
    bottomPadding: DividerBottomPadding = DividerBottomPadding.Default,
) {

    val topPad = when (topPadding) {
        DividerTopPadding.Default -> 12.dp
        DividerTopPadding.Double -> 24.dp
        DividerTopPadding.Zero -> 0.dp
        DividerTopPadding.OneAndHalf -> 18.dp
        DividerTopPadding.Half -> 6.dp
    }
    val startPad = when (startPadding) {
        DividerStartPadding.Default -> ScreenPadding.getStartPadding()
        DividerStartPadding.Zero -> 0.dp
    }
    val endPad = when (endPadding) {
        DividerEndPadding.Default -> ScreenPadding.getEndPadding()
        DividerEndPadding.Zero -> 0.dp
    }
    val bottomPad = when (bottomPadding) {
        DividerBottomPadding.Default -> 12.dp
        DividerBottomPadding.Zero -> 0.dp
        DividerBottomPadding.OneAndHalf -> 18.dp
        DividerBottomPadding.Half -> 6.dp
        DividerBottomPadding.Double -> 24.dp
    }

    HorizontalDivider(
        modifier = Modifier.padding(
            start = startPad,
            end = endPad,
            top = topPad,
            bottom = bottomPad
        ),
        thickness = 0.5.dp
    )
}

enum class DividerTopPadding {
    Default,
    Double,
    Half,
    Zero,
    OneAndHalf
}

enum class DividerBottomPadding {
    Default,
    Double,
    Half,
    Zero,
    OneAndHalf
}

enum class DividerStartPadding {
    Default,
    Zero
}

enum class DividerEndPadding {
    Default,
    Zero
}