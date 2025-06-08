package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.components.CustomRating
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemVerticalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.getMovieGenres
import com.irfangujjar.tmdb.core.ui.util.getTvShowsGenres
import com.irfangujjar.tmdb.core.ui.util.imageType
import com.irfangujjar.tmdb.core.ui.util.isMovie

@Composable
fun MediaItemVertical(
    preview: Boolean, values: MediaItemVerticalValues,
    onItemClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = {
                onItemClicked(values.mediaId)
            })
            .padding(end = 24.dp),
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = values.mediaType.imageType(),
            imageUrl = values.posterPath,
            width = 63.dp,
            height = 85.dp,
            contentScale = ContentScale.Crop,
            placeHolderSize = 60.dp,
            posterSize = PosterSizes.w92,
        )
        Column(
            modifier = Modifier
                .width(250.dp)
                .padding(top = 4.dp, end = 12.dp)
        ) {
            Text(
                values.mediaTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp)
            )
            Text(
                text = if (values.mediaType.isMovie()) getMovieGenres(values.mediaGenre)
                else getTvShowsGenres(values.mediaGenre),
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(start = 8.dp)
            )
            CustomRating(
                voteAverage = values.voteAverage, voteCount = values.voteCount,
                padding = PaddingValues(start = 6.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically),
        )
    }
}

@Preview
@Composable
private fun MediaItemVerticalPreview() {
    TMDbTheme {
        Surface {
            MediaItemVertical(
                preview = true,
                values = MediaItemVerticalValues.dummyData(mediaType = MediaType.Movie)
            ) {

            }
        }
    }
}