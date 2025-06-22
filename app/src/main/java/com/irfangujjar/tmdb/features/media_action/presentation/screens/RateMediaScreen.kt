package com.irfangujjar.tmdb.features.media_action.presentation.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.core.api.mediaImageType
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomConfirmDialogBox
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomMessageDialogBox
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.image.network.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.media_action.presentation.viewmodels.RateMediaViewModel

@Composable
fun RateMediaScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    onBackStackPressed: () -> Unit,
    key: HomeNavKey.RateMediaNavKey,
    viewModel: RateMediaViewModel = hiltViewModel(),
    onRatingUpdated: (MediaStateType, Int, Int, Boolean) -> Unit
) {

    viewModel.initialize(key)

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = key.title, showBackStack = true,
                onBackStackPressed = onBackStackPressed,
                navigationIcon = Icons.Default.Close
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            RateMediaBody(
                preview = preview,
                outerPadding = outerPadding,
                innerPadding = innerPadding,
                key = key,
                isLoading = viewModel.isLoading,
                currentRating = viewModel.currentRating,
                onRatingChanged = {
                    viewModel.changeRating(it)
                },
                onRate = {
                    viewModel.rate {
                        onRatingUpdated(
                            key.mediaStateType,
                            key.mediaId,
                            viewModel.currentRating,
                            true
                        )
                    }
                },
                onUnRate = {
                    viewModel.showUnRateConfirmationDialog()
                }

            )
        }
    }

    if (viewModel.showAlert) {
        CustomMessageDialogBox(
            title = "Error",
            message = viewModel.alertMessage,
            onDismiss = {
                viewModel.clearAlert()
            }
        )
    }

    if (viewModel.showUnRateConfirmationDialog) {
        CustomConfirmDialogBox(
            message = "Do you really want to remove rating?",
            onDismiss = {
                viewModel.hideUnRateConfirmationDialog()
            },
            onConfirm = {
                viewModel.unRate {
                    onRatingUpdated(
                        key.mediaStateType,
                        key.mediaId,
                        viewModel.currentRating,
                        false
                    )
                }
            })
    }
}


@Composable
private fun RateMediaBody(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    key: HomeNavKey.RateMediaNavKey,
    currentRating: Int,
    isLoading: Boolean,
    onRatingChanged: (Int) -> Unit,
    onRate: () -> Unit,
    onUnRate: () -> Unit
) {
    val scrollState = rememberScrollState()
    Box {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = key.mediaStateType.mediaImageType(),
            imageUrl = key.posterPath,
            width = 0.dp,
            height = 0.dp,
            contentScale = ContentScale.Crop,
            movieTvBorderDecoration = false,
            placeHolderSize = 96.dp,
            posterSize = PosterSizes.w185,
            backgroundTransparency = 0.9f
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    ScreenPadding.getPadding(
                        outerPaddingValues = outerPadding,
                        innerPaddingValues = innerPadding,
                    )
                )
                .padding(top = 24.dp)
        ) {
            CustomNetworkImage(
                preview = preview,
                isLandscape = false,
                type = key.mediaStateType.mediaImageType(),
                imageUrl = key.posterPath,
                width = 150.dp,
                height = 225.dp,
                contentScale = ContentScale.Crop,
                placeHolderSize = 72.dp,
                posterSize = PosterSizes.w185,
            )
            Text(
                currentRating.toString(), fontSize = 28.sp, fontWeight = FontWeight.W500,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 18.dp)
            )
            RatingComp(
                currentRating = currentRating,
                onRatingChanged = {
                    if (!isLoading)
                        onRatingChanged(it)
                }
            )
            Button(
                enabled = currentRating != 0 && key.rating.toInt() != currentRating && !isLoading,
                onClick = onRate,
            ) {
                Text("Rate")
            }
            if (key.rating != 0.0)
                Button(
                    enabled = !isLoading,
                    onClick = onUnRate,
                    modifier = Modifier.padding(top = 12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text("Delete Rating")
                }

        }
        if (isLoading)
            CustomLoading()
    }
}

@Composable
private fun RatingComp(
    currentRating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.padding(vertical = 18.dp)
    ) {
        repeat(10) { index ->
            val ratingNo = index + 1
            val color: Color
            val icon: ImageVector
            if (ratingNo <= currentRating) {
                color = MaterialTheme.colorScheme.primary
                icon = Icons.Filled.Star
            } else {
                color = Color.Gray
                icon = Icons.Outlined.StarBorder
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(
                    start = if (index == 0) 4.dp else 0.dp,
                    end = if (index == 9) 0.dp else 4.dp
                )
            ) {
                Icon(
                    imageVector = icon, contentDescription = null,
                    tint = color,
                    modifier = Modifier
                        .size(28.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                onRatingChanged(ratingNo)
                            })
                        },
                )
            }
        }
    }
}

@Preview
@Composable
private fun RateMediaBodyPreview() {
    TMDbTheme {
        Surface {
            val movie = MovieModel.dummyData()
            RateMediaBody(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                currentRating = 8,
                isLoading = false,
                key = HomeNavKey.RateMediaNavKey(
                    mediaStateType = MediaStateType.Movie,
                    mediaId = movie.id,
                    title = movie.title,
                    posterPath = movie.posterPath,
                    rating = 8.0
                ),
                onRatingChanged = {},
                onRate = {},
                onUnRate = {}
            )
        }
    }
}