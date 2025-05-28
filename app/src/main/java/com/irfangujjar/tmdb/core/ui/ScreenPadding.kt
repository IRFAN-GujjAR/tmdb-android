package com.irfangujjar.tmdb.core.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object ScreenPadding {
    private val TOP_PADDING = 24.dp
    private val BOTTOM_PADDING = 48.dp
    private val START_PADDING = 12.dp
    private val END_PADDING = 12.dp

//    const val TOP_BAR_HEIGHT = 56 // Material Design Top App Bar height in dp
//    const val BOTTOM_BAR_HEIGHT = 56 // Material Design Bottom Navigation Bar height in dp

    fun getPadding(
        outerPaddingValues: PaddingValues? = null,
        innerPaddingValues: PaddingValues? = null,
        includeTopPadding: Boolean = true,
        includeBottomPadding: Boolean = true,
        includeStartPadding: Boolean = true,
        includeEndPadding: Boolean = true,
    ): PaddingValues {
        return PaddingValues(
            top = getTopPadding(
                outerPaddingValues = outerPaddingValues,
                innerPaddingValues = innerPaddingValues,
                includeSpacing = includeTopPadding
            ),
            bottom = getBottomPadding(
                outerPaddingValues = outerPaddingValues,
                innerPaddingValues = innerPaddingValues,
                includeSpacing = includeBottomPadding
            ),
            start = if (includeStartPadding) START_PADDING else 0.dp,
            end = if (includeEndPadding) END_PADDING else 0.dp
        )
    }

    fun getTopPadding(
        outerPaddingValues: PaddingValues? = null,
        innerPaddingValues: PaddingValues? = null,
        includeSpacing: Boolean = true,
    ): Dp {
        var topPadding = 0.dp
        if (includeSpacing)
            topPadding = TOP_PADDING
        if (innerPaddingValues != null) {
            topPadding += innerPaddingValues.calculateTopPadding()
        } else if (outerPaddingValues != null) {
            topPadding += outerPaddingValues.calculateTopPadding()
        }
        
        return topPadding
    }

    fun getBottomPadding(
        outerPaddingValues: PaddingValues? = null,
        innerPaddingValues: PaddingValues? = null,
        includeSpacing: Boolean = true,
    ): Dp {
        var bottomPadding = 0.dp
        if (includeSpacing)
            bottomPadding = BOTTOM_PADDING

        if (outerPaddingValues != null) {
            bottomPadding += outerPaddingValues.calculateBottomPadding()
        }
        if (innerPaddingValues != null) {
            bottomPadding += innerPaddingValues.calculateBottomPadding()
        }
        return bottomPadding
    }

    fun getHorizontalPadding(): Dp = START_PADDING
    fun getStartPadding(): Dp = START_PADDING
    fun getEndPadding(): Dp = END_PADDING

}