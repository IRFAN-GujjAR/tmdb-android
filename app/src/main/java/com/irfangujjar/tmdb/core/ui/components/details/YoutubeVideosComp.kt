package com.irfangujjar.tmdb.core.ui.components.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.models.VideosModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.urls.URLS


@Composable
fun YoutubeVideosComp(
    preview: Boolean,
    videos: VideosModel
) {
    val context = LocalContext.current
    Column {
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = DividerBottomPadding.OneAndHalf
        )
        TextRow(title = "Videos")
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            items(videos.videos) { video ->
                Box(
                    modifier = Modifier
                        .size(
                            height = 90.dp,
                            width = 160.dp
                        )
                        .border(width = 1.dp, color = Color.Gray)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                URLS.openYoutubeVideo(
                                    context = context,
                                    videoId = video.key,
                                )
                            })
                        }
                ) {
                    AsyncImage(
                        model = if (preview) R.drawable.movie_youtube_thumbnail_placeholder
                        else URLS.getYoutubeThumbnail(videoId = video.key),
                        contentDescription = video.name,
                        modifier = Modifier.size(height = 90.dp, width = 160.dp),
                        contentScale = ContentScale.Crop,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(31.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black
                                    ),
                                )
                            )
                            .align(Alignment.BottomCenter)
                    )
                    Box(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomEnd)
                            .padding(end = 8.dp, bottom = 8.dp)
                            .background(
                                color = Color(0xFFB7B5C5), shape = RoundedCornerShape(4.dp)
                            )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.youtube_logo),
                            contentDescription = "YouTube Logo",
                            modifier = Modifier
                                .padding(horizontal = 3.dp)
                                .size(20.dp),
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun YoutubeVideosCompPreview() {
    TMDbTheme {
        Surface {
            YoutubeVideosComp(
                preview = true,
                videos = VideosModel.dummyData(type = MediaType.Movie)
            )
        }
    }
}