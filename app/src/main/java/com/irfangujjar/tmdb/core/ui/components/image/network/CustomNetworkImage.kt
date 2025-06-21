package com.irfangujjar.tmdb.core.ui.components.image.network

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.components.image.CelebrityProfilePlaceholder
import com.irfangujjar.tmdb.core.ui.components.image.MoviePosterPlaceholder
import com.irfangujjar.tmdb.core.ui.components.image.TvPosterPlaceholder
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.core.ui.util.StillSizes
import com.irfangujjar.tmdb.core.ui.util.getUrl


@Composable
fun CustomNetworkImage(
    preview: Boolean = false,
    isLandscape: Boolean,
    type: MediaImageType,
    imageUrl: String?,
    width: Dp,
    height: Dp,
    contentScale: ContentScale,
    borderRadius: Dp = 0.dp,
    movieTvBorderDecoration: Boolean = true,
    placeHolderSize: Dp,
    celebrityPlaceHolderCircularShape: Boolean = false,
    backgroundTransparency: Float? = null,
    posterSize: PosterSizes = PosterSizes.w185,
    backdropSize: BackdropSizes = BackdropSizes.w300,
    profileSize: ProfileSizes = ProfileSizes.w185,
    stillSize: StillSizes = StillSizes.w185
) {
    if (type == MediaImageType.Celebrity) {
        Celebrity(
            preview = preview,
            type = type,
            imageUrl = imageUrl,
            width = width,
            height = height,
            celebrityPlaceHolderCircularShape = celebrityPlaceHolderCircularShape,
            placeHolderSize = placeHolderSize,
            borderRadius = borderRadius,
            contentScale = contentScale,
            profileSize = profileSize,
            backgroundTransparency = backgroundTransparency
        )
    } else {
        var modifier = if (height == 0.dp) {
            Modifier.fillMaxHeight()
        } else {
            Modifier.height(height)
        }
        modifier = if (width == 0.dp) {
            modifier.fillMaxWidth()
        } else {
            modifier.width(width = width)
        }
        if (movieTvBorderDecoration) {
            modifier = modifier.border(
                width = 0.5.dp, color = Color.Gray,
                shape = RectangleShape
            )
        }
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            if (imageUrl == null) {
                if (type == MediaImageType.Movie)
                    MoviePosterPlaceholder(placeHolderSize)
                else
                    TvPosterPlaceholder(placeHolderSize)
            } else {
                val url: String = if (type == MediaImageType.Episode) {
                    stillSize.getUrl(imageUrl)
                } else {
                    if (isLandscape) backdropSize.getUrl(imageUrl) else posterSize.getUrl(
                        imageUrl
                    )
                }
                NetworkImageComp(
                    preview = preview,
                    mediaImageType = type,
                    imagePath = url,
                    contentScale = contentScale,
                    isLandscape = isLandscape,
                    posterSize = posterSize,
                    backdropSizes = backdropSize,
                    stillSize = stillSize,
                    backgroundTransparency = backgroundTransparency
                )
            }
        }
    }
}


@Composable
private fun Celebrity(
    preview: Boolean,
    type: MediaImageType,
    imageUrl: String?,
    width: Dp,
    height: Dp,
    celebrityPlaceHolderCircularShape: Boolean,
    placeHolderSize: Dp,
    borderRadius: Dp,
    contentScale: ContentScale,
    profileSize: ProfileSizes,
    backgroundTransparency: Float?
) {
    if (imageUrl == null && celebrityPlaceHolderCircularShape) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(width = width, height = height)
                .clip(CircleShape)
                .border(
                    width = 0.5.dp,
                    shape = CircleShape,
                    color = Color.Gray
                )
        ) {
            CelebrityProfilePlaceholder(
                size = placeHolderSize,
                modifier = Modifier.padding(10.dp)
            )
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(
                    width = width,
                    height = height
                )
                .border(
                    width = 0.5.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(borderRadius)
                )
                .clip(RoundedCornerShape(borderRadius))

        ) {
            if (imageUrl == null) {
                CelebrityProfilePlaceholder(size = placeHolderSize)
            } else {
                NetworkImageComp(
                    mediaImageType = type,
                    preview = preview,
                    imagePath = profileSize.getUrl(imageUrl),
                    contentScale = contentScale,
                    profileSize = profileSize,
                    backgroundTransparency = backgroundTransparency
                )
            }
        }
    }
}





