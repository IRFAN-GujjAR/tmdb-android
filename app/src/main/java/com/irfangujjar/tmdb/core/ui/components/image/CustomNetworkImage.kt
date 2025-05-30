package com.irfangujjar.tmdb.core.ui.components.image

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
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
    posterSize: PosterSizes = PosterSizes.w185,
    backdropSize: BackdropSizes = BackdropSizes.w300,
    profileSize: ProfileSizes = ProfileSizes.w185
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
        )
    } else {
        var modifier = Modifier
            .size(width = width, height = height)
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
                NetworkImage(
                    preview = preview,
                    mediaImageType = type,
                    imagePath = if (isLandscape) backdropSize.getUrl(imageUrl) else posterSize.getUrl(
                        imageUrl
                    ),
                    contentScale = contentScale,
                    isLandscape = isLandscape,
                    posterSize = posterSize,
                    backdropSizes = backdropSize
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
    profileSize: ProfileSizes
) {
    if (imageUrl == null && celebrityPlaceHolderCircularShape) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
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
                NetworkImage(
                    mediaImageType = type,
                    preview = preview,
                    imagePath = profileSize.getUrl(imageUrl),
                    contentScale = contentScale,
                    profileSize = profileSize
                )
            }
        }
    }
}


@Composable
private fun NetworkImage(
    mediaImageType: MediaImageType,
    modifier: Modifier = Modifier,
    preview: Boolean = false,
    imagePath: String,
    contentScale: ContentScale = ContentScale.Crop,
    isLandscape: Boolean=false,
    posterSize: PosterSizes = PosterSizes.w185,
    backdropSizes: BackdropSizes = BackdropSizes.w300,
    profileSize: ProfileSizes = ProfileSizes.w92
) {
    AsyncImage(
        model = if (preview) dummyPreviewImage(
            mediaImageType,
            isLandscape,
            backdropSizes,
            posterSize,
            profileSize
        ) else ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = contentScale,
        modifier = modifier.fillMaxSize()
    )
}

private fun dummyPreviewImage(
    mediaImageType: MediaImageType,
    isLandscape: Boolean=false,
    backdropSizes: BackdropSizes,
    posterSize: PosterSizes,
    profileSize: ProfileSizes
): Int = when (mediaImageType) {
    MediaImageType.Movie -> {
        if (isLandscape) {
            when (backdropSizes) {
                BackdropSizes.w300 -> R.drawable.movie_backdrop_w300
                BackdropSizes.w780 -> R.drawable.movie_backdrop_w780
                BackdropSizes.w1280 -> R.drawable.movie_backdrop_w1280
                BackdropSizes.original -> R.drawable.movie_backdrop_original
            }
        } else {
            when (posterSize) {
                PosterSizes.w92 -> R.drawable.movie_poster_w92
                PosterSizes.w154 -> R.drawable.movie_poster_w154
                PosterSizes.w185 -> R.drawable.movie_poster_w185
                PosterSizes.w342 -> R.drawable.movie_poster_w342
                PosterSizes.w500 -> R.drawable.movie_poster_w500
                PosterSizes.w780 -> R.drawable.movie_poster_w780
                PosterSizes.original -> R.drawable.movie_poster_original
            }
        }

    }

    MediaImageType.TvShow -> {
        if (isLandscape) {
            when (backdropSizes) {
                BackdropSizes.w300 -> R.drawable.tv_backdrop_w300
                BackdropSizes.w780 -> R.drawable.tv_backdrop_w780
                BackdropSizes.w1280 -> R.drawable.tv_backdrop_w1280
                BackdropSizes.original -> R.drawable.tv_backdrop_original
            }
        } else {
            when (posterSize) {
                PosterSizes.w92 -> R.drawable.tv_poster_w92
                PosterSizes.w154 -> R.drawable.tv_poster_w154
                PosterSizes.w185 -> R.drawable.tv_poster_w185
                PosterSizes.w342 -> R.drawable.tv_poster_w342
                PosterSizes.w500 -> R.drawable.tv_poster_w500
                PosterSizes.w780 -> R.drawable.tv_poster_w780
                PosterSizes.original -> R.drawable.tv_poster_original
            }
        }
    }

    MediaImageType.Celebrity -> {
        when (profileSize) {
            ProfileSizes.w45 -> R.drawable.celebrity_profile_w45
            ProfileSizes.w92 -> R.drawable.celebrity_profile_w92
            ProfileSizes.w185 -> R.drawable.celebrity_profile_w185
            ProfileSizes.h632 -> R.drawable.celebrity_profile_h632
            ProfileSizes.original -> R.drawable.celebrity_profile_original
        }
    }
}
