package com.irfangujjar.tmdb.core.ui.components.list.values

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes

data class CelebItemHorizontalConfigValues(
    val listViewHeight: Dp,
    val listItemWidth: Dp,
    val imageHeight: Dp,
    val font: TextUnit,
    val profileSize: ProfileSizes
) {

    companion object {
        fun fromDefault() = CelebItemHorizontalConfigValues(
            listViewHeight = 212.dp,
            listItemWidth = 99.dp,
            imageHeight = 139.dp,
            font = 12.sp,
            profileSize = ProfileSizes.w185,
        )
    }
}
