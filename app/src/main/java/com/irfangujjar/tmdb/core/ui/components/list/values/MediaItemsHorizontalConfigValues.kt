package com.irfangujjar.tmdb.core.ui.components.list.values

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MoviesCategories
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.TvShowsCategories


data class MediaItemsHorizontalConfigValues(
//    val listViewHeight: Dp,
    val listItemWidth: Dp,
    val imageHeight: Dp,
    val font: TextUnit,
    val posterSize: PosterSizes,
    val backdropSize: BackdropSizes
) {
    companion object {
        fun fromDefault() = MediaItemsHorizontalConfigValues(
//            listViewHeight = 205.dp,
            listItemWidth = 99.dp,
            imageHeight = 139.dp,
            font = 12.sp,
            posterSize = PosterSizes.w185,
            backdropSize = BackdropSizes.w300,
        )

        fun dummyDataMovie(
            category: MoviesCategories,
        ): MediaItemsHorizontalConfigValues {
            var font = 12.sp
            var listItemWidth = 99.dp
//            var listViewHeight = 200.dp
            var imageHeight = 139.dp
            var posterSize = PosterSizes.w185
            when (category) {
                MoviesCategories.Popular -> {
                    font = 13.sp
                    listItemWidth = 107.dp
//                    listViewHeight = 220.dp
                    imageHeight = 150.dp
                }

                MoviesCategories.InTheatres -> {
                    font = 15.sp
                    listItemWidth = 209.dp
//                    listViewHeight = 170.dp
                    imageHeight = 122.dp
                }

                MoviesCategories.TopRated -> {
                    listItemWidth = 120.dp
//                    listViewHeight = 240.dp
                    imageHeight = 180.dp
                    posterSize = PosterSizes.w92
                }

                MoviesCategories.DetailsRecommended -> {
//                    listViewHeight = 205.dp
                }

                MoviesCategories.DetailsSimilar -> {
//                    listViewHeight = 205.dp
                }

                else -> {}
            }
            return MediaItemsHorizontalConfigValues(
//                listViewHeight = listViewHeight,
                listItemWidth = listItemWidth,
                imageHeight = imageHeight,
                font = font,
                posterSize = posterSize,
                backdropSize = BackdropSizes.w300,
            )
        }


        fun dummyDataTv(
            category: TvShowsCategories
        ): MediaItemsHorizontalConfigValues {
            var font = 12.sp
            var listItemWidth = 99.dp
//            var listViewHeight = 205.dp
            var imageHeight = 139.dp
            var posterSize = PosterSizes.w185
            when (category) {
                TvShowsCategories.AiringToday -> {
                    font = 15.sp
                    listItemWidth = 209.dp
//                    listViewHeight = 170.dp
                    imageHeight = 122.dp

                }

                TvShowsCategories.Trending -> {
//                    listViewHeight = 200.dp
                }

                TvShowsCategories.TopRated -> {
                    listItemWidth = 120.dp
//                    listViewHeight = 240.dp
                    imageHeight = 180.dp
                    posterSize = PosterSizes.w92
                }

                TvShowsCategories.Popular -> {
                    listItemWidth = 107.dp
//                    listViewHeight = 220.dp
                    imageHeight = 150.dp
                }

                else -> {}
            }

            return MediaItemsHorizontalConfigValues(
//                listViewHeight = listViewHeight,
                listItemWidth = listItemWidth,
                imageHeight = imageHeight,
                font = font,
                posterSize = posterSize,
                backdropSize = BackdropSizes.w300,
            )
        }
    }
}

