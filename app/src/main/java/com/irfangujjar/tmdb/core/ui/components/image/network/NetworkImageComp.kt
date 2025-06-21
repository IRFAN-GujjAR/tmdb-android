package com.irfangujjar.tmdb.core.ui.components.image.network

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.core.ui.util.StillSizes

@Composable
fun NetworkImageComp(
    mediaImageType: MediaImageType,
    modifier: Modifier = Modifier,
    preview: Boolean = false,
    imagePath: String,
    contentScale: ContentScale = ContentScale.Crop,
    isLandscape: Boolean = false,
    backgroundTransparency: Float? = null,
    posterSize: PosterSizes = PosterSizes.w185,
    backdropSizes: BackdropSizes = BackdropSizes.w300,
    profileSize: ProfileSizes = ProfileSizes.w92,
    stillSize: StillSizes = StillSizes.w185
) {
    var newModifier = modifier.fillMaxSize()
    if (backgroundTransparency != null) {
        Box(newModifier) {
            AsyncImage(
                model = if (preview) dummyPreviewImage(
                    mediaImageType,
                    isLandscape,
                    backdropSizes,
                    posterSize,
                    profileSize,
                    stillSize
                ) else ImageRequest.Builder(LocalContext.current)
                    .data(imagePath)
                    .crossfade(true)
                    .build(),
                contentDescription = "Image",
                contentScale = contentScale,
                modifier = newModifier
            )
            Box(
                modifier = newModifier.background(
                    color = Color.Transparent.copy(
                        backgroundTransparency
                    )
                )
            )
        }
        return
    }
    AsyncImage(
        model = if (preview) dummyPreviewImage(
            mediaImageType,
            isLandscape,
            backdropSizes,
            posterSize,
            profileSize,
            stillSize
        ) else ImageRequest.Builder(LocalContext.current)
            .data(imagePath)
            .crossfade(true)
            .build(),
        contentDescription = "Image",
        contentScale = contentScale,
        modifier = newModifier
    )
}
