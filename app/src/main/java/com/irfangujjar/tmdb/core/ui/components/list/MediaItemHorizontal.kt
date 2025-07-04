package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.components.image.network.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MoviesCategory
import com.irfangujjar.tmdb.core.ui.util.getMovieGenres
import com.irfangujjar.tmdb.core.ui.util.getTvShowsGenres
import com.irfangujjar.tmdb.core.ui.util.imageType
import com.irfangujjar.tmdb.core.ui.util.isMovie


@Composable
fun MediaItemHorizontal(
    preview: Boolean = false, values: MediaItemHorizontalValues,
    onItemTapped: (Int, String, String?, String?) -> Unit
) {
    Box(
        modifier = Modifier
            .width(values.configValues.listItemWidth)
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onItemTapped(
                        values.mediaId,
                        values.mediaTitle,
                        values.posterPath,
                        values.backdropPath
                    )
                })
            }
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Box(
                modifier = Modifier
                    .height(values.configValues.imageHeight)
                    .fillMaxWidth()
            ) {
                CustomNetworkImage(
                    preview = preview,
                    type = values.mediaType.imageType(),
                    imageUrl = if (values.isLandscape) values.backdropPath else values.posterPath,
                    isLandscape = values.isLandscape,
                    width = values.configValues.listItemWidth,
                    height = values.configValues.imageHeight,
                    contentScale = ContentScale.Crop,
                    placeHolderSize = if (values.isLandscape) 84.dp else 72.dp,
                    posterSize = values.configValues.posterSize,
                    backdropSize = values.configValues.backdropSize
                )
            }
            Text(
                text = values.mediaTitle,
                maxLines = if (values.isLandscape) 1 else 2,
                fontWeight = FontWeight.W500,
                fontSize = values.configValues.font,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 2.dp, start = 1.dp),
            )
            if (!values.mediaGenre.isNullOrEmpty())
                Text(
                    text = if (values.mediaType.isMovie()) getMovieGenres(values.mediaGenre)
                    else getTvShowsGenres(values.mediaGenre),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = if (values.isLandscape) 12.sp else 11.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 1.dp),
                )
        }
    }
}

@Preview
@Composable
private fun MediaItemHorizontalPreview() {
    TMDbTheme {
        Surface {
            MediaItemHorizontal(
                preview = true,
                values = MediaItemHorizontalValues.dummyDataMovie(
                    category =
                        MoviesCategory.Popular,
                    isLandscape = false
                ),
                onItemTapped = { _, _, _, _ -> }
            )
        }
    }
}


