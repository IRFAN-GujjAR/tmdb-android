package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.image.network.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel


@Composable
fun CollectionBackdropComp(
    preview: Boolean,
    name: String, overview: String?,
    posterPath: String?, backdropPath: String?
) {
    Box {
        Box {
            CustomNetworkImage(
                preview = preview,
                isLandscape = true,
                type = MediaImageType.Movie,
                imageUrl = backdropPath,
                width = 0.dp,
                height = 211.dp,
                contentScale = ContentScale.FillWidth,
                borderRadius = 0.dp,
                movieTvBorderDecoration = false,
                placeHolderSize = 84.dp,
                backdropSize = BackdropSizes.w780,
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(76.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.surface
                            ),
                        )
                    )
                    .align(Alignment.BottomCenter)
            )
        }
        Box(
            modifier = Modifier
                .align(
                    alignment = Alignment.BottomEnd
                )
                .padding(
                    top = 202.dp,
                    start = ScreenPadding.getStartPadding(),
                    end = ScreenPadding.getEndPadding()
                )
        ) {
            Box {
                Column(
                    modifier = Modifier.padding(end = 92.dp + 24.dp)
                ) {
                    Text(name, fontSize = 18.sp, fontWeight = FontWeight.W500)
                    if (!overview.isNullOrBlank())
                        Text(
                            overview + overview, fontSize = 13.sp, fontWeight = FontWeight.W500,
                            color = Color.Gray
                        )
                }
                Box(modifier = Modifier.align(alignment = Alignment.TopEnd)) {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.Movie,
                        imageUrl = posterPath,
                        width = 92.dp,
                        height = 136.dp,
                        contentScale = ContentScale.FillWidth,
                        borderRadius = 0.dp,
                        movieTvBorderDecoration = false,
                        placeHolderSize = 60.dp,
                        posterSize = PosterSizes.w185,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CollectionBackdropCompPreview() {
    TMDbTheme {
        Surface {
            val collection = CollectionDetailsModel.dummyData()
            CollectionBackdropComp(
                preview = true,
                name = collection.name,
                overview = collection.overview,
                posterPath = collection.posterPath,
                backdropPath = collection.backdropPath
            )
        }
    }
}