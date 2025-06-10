package com.irfangujjar.tmdb.core.ui.components.list.values

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategory


data class MediaItemsHorizontalListConfigValues(
    val listViewHeight: Dp,
    val listItemWidth: Dp,
    val imageHeight: Dp,
    val font: TextUnit,
    val posterSize: PosterSizes,
    val backdropSize: BackdropSizes
) {
    companion object {
        fun fromDefault() = MediaItemsHorizontalListConfigValues(
            listViewHeight = 210.dp,
            listItemWidth = 99.dp,
            imageHeight = 139.dp,
            font = 12.sp,
            posterSize = PosterSizes.w185,
            backdropSize = BackdropSizes.w300,
        )

        fun movieConfig(
            category: MoviesCategory,
        ): MediaItemsHorizontalListConfigValues {
            val default = fromDefault()
            var font = default.font
            var listItemWidth = default.listItemWidth
            var listViewHeight = default.listViewHeight
            var imageHeight = default.imageHeight
            var posterSize = default.posterSize
            when (category) {
                MoviesCategory.Popular -> {
                    font = 13.sp
                    listItemWidth = 107.dp
                    listViewHeight = 220.dp
                    imageHeight = 150.dp
                }

                MoviesCategory.InTheatres -> {
                    font = 15.sp
                    listItemWidth = 209.dp
                    listViewHeight = 170.dp
                    imageHeight = 122.dp
                }

                MoviesCategory.TopRated -> {
                    listItemWidth = 120.dp
                    listViewHeight = 240.dp
                    imageHeight = 180.dp
                    posterSize = PosterSizes.w92
                }

                else -> {}
            }
            return MediaItemsHorizontalListConfigValues(
                listViewHeight = listViewHeight,
                listItemWidth = listItemWidth,
                imageHeight = imageHeight,
                font = font,
                posterSize = posterSize,
                backdropSize = default.backdropSize,
            )
        }


        fun tvConfig(
            category: TvShowsCategory
        ): MediaItemsHorizontalListConfigValues {
            val default = fromDefault()
            var font = default.font
            var listItemWidth = default.listItemWidth
            var listViewHeight = default.listViewHeight
            var imageHeight = default.imageHeight
            var posterSize = default.posterSize
            when (category) {
                TvShowsCategory.AiringToday -> {
                    font = 15.sp
                    listItemWidth = 209.dp
                    listViewHeight = 170.dp
                    imageHeight = 122.dp

                }


                TvShowsCategory.TopRated -> {
                    listItemWidth = 120.dp
                    listViewHeight = 240.dp
                    imageHeight = 180.dp
                    posterSize = PosterSizes.w92
                }

                TvShowsCategory.Popular -> {
                    listItemWidth = 107.dp
                    listViewHeight = 220.dp
                    imageHeight = 150.dp
                }

                else -> {}
            }

            return MediaItemsHorizontalListConfigValues(
                listViewHeight = listViewHeight,
                listItemWidth = listItemWidth,
                imageHeight = imageHeight,
                font = font,
                posterSize = posterSize,
                backdropSize = default.backdropSize,
            )
        }
    }
}

