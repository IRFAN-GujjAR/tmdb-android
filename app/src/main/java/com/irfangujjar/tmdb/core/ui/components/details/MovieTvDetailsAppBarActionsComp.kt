package com.irfangujjar.tmdb.core.ui.components.details

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.api.MediaStateType
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.components.CustomConfirmDialogBox
import com.irfangujjar.tmdb.core.ui.components.NotSignedInDialogBox
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusState
import com.irfangujjar.tmdb.features.media_action.presentation.viewmodels.MediaActionViewModel
import com.irfangujjar.tmdb.features.media_action.presentation.viewmodels.states.MediaActionState
import com.irfangujjar.tmdb.features.media_state.presentation.viewmodels.states.MediaStateState


@Composable
fun MovieTvDetailsAppBarActionsComp(
    snackBarHostState: SnackbarHostState,
    mediaId: Int,
    title: String,
    posterPath: String?,
    mediaStateType: MediaStateType,
    state: MediaStateState,
    signInState: SignInStatusState,
    onReloadMediaState: () -> Unit,
    mediaActionViewModel: MediaActionViewModel = hiltViewModel(),
    onNavigateToRateMedia: (HomeNavKey.RateMediaNavKey) -> Unit,
) {
    val mediaActionState = mediaActionViewModel.state.collectAsState().value
    val isLoading = mediaActionState is MediaActionState.Loading

    mediaActionViewModel.updateSignInStatus(signInState)
    mediaActionViewModel.updateMediaState(state)


    if (state is MediaStateState.Error) {
        IconButton(onClick = {
            onReloadMediaState()
        }) {
            Icon(
                imageVector = Icons.Default.Error, contentDescription = null,
                tint = Color.Red
            )
        }
    } else {
        IconAction(
            icon = favoriteIcon(
                isSignedIn = mediaActionViewModel.isSignedIn,
                isFavorite = mediaActionViewModel.isFavorite
            ),
            isLoading = mediaActionViewModel.isMediaStateLoading || isLoading,
            isSignedIn = mediaActionViewModel.isSignedIn,
            onShowNotLoggedInDialogBox = {
                mediaActionViewModel.showNotSignedInDialog()
            },
            onClick = {
                if (mediaActionViewModel.isFavorite) {
                    mediaActionViewModel.showFavoriteRemovalDialog()
                } else {
                    mediaActionViewModel.favorite(
                        mediaId = mediaId, mediaType = mediaStateType,
                        favorite = true,
                        onFavoriteSuccess = onReloadMediaState
                    )
                }
            }
        )
        IconAction(
            icon = ratedIcon(
                isSignedIn = mediaActionViewModel.isSignedIn,
                isRated = mediaActionViewModel.isRated
            ),
            isLoading = mediaActionViewModel.isMediaStateLoading || isLoading,
            isSignedIn = mediaActionViewModel.isSignedIn,
            onShowNotLoggedInDialogBox = {
                mediaActionViewModel.showNotSignedInDialog()
            },
            onClick = {
                onNavigateToRateMedia(
                    HomeNavKey.RateMediaNavKey(
                        mediaStateType = mediaStateType,
                        mediaId = mediaId,
                        title = title,
                        posterPath = posterPath,
                        rating = mediaActionViewModel.rating
                    )
                )
            }
        )
        IconAction(
            icon = watchlistIcon(
                isSignedIn = mediaActionViewModel.isSignedIn,
                isWatchlist = mediaActionViewModel.isWatchlist
            ),
            isLoading = mediaActionViewModel.isMediaStateLoading || isLoading,
            isSignedIn = mediaActionViewModel.isSignedIn,
            onShowNotLoggedInDialogBox = {
                mediaActionViewModel.showNotSignedInDialog()
            },
            onClick = {
                if (mediaActionViewModel.isWatchlist) {
                    mediaActionViewModel.showWatchlistRemovalDialog()
                } else {
                    mediaActionViewModel.watchlist(
                        mediaId = mediaId, mediaType = mediaStateType,
                        watchlist = true,
                        onWatchlistSuccess = onReloadMediaState
                    )
                }
            }
        )
    }

    if (mediaActionViewModel.showNotSignedInDialog) {
        NotSignedInDialogBox(onDismiss = {
            mediaActionViewModel.hideNotSignedInDialog()
        })
    }

    if (mediaActionViewModel.showFavoriteRemovalDialog) {
        CustomConfirmDialogBox(
            message = "Do you really want to remove this from favorite?",
            onDismiss = {
                mediaActionViewModel.hideFavoriteRemovalDialog()
            },
            onConfirm = {
                mediaActionViewModel.favorite(
                    mediaId = mediaId, mediaType = mediaStateType,
                    favorite = false,
                    onFavoriteSuccess = onReloadMediaState
                )
            }
        )
    }

    if (mediaActionViewModel.showWatchlistRemovalDialog) {
        CustomConfirmDialogBox(
            message = "Do you really want to remove this from watchlist?",
            onDismiss = {
                mediaActionViewModel.hideWatchlistRemovalDialog()
            },
            onConfirm = {
                mediaActionViewModel.watchlist(
                    mediaId = mediaId, mediaType = mediaStateType,
                    watchlist = false,
                    onWatchlistSuccess = onReloadMediaState
                )
            }
        )
    }

    if (mediaActionViewModel.showAlert) {
        LaunchedEffect(mediaActionViewModel.showAlert) {
            snackBarHostState.showSnackbar(mediaActionViewModel.alertMessage)
            mediaActionViewModel.clearAlert()
        }
    }
}

@Composable
private fun IconAction(
    icon: ImageVector,
    isLoading: Boolean,
    isSignedIn: Boolean,
    onShowNotLoggedInDialogBox: () -> Unit,
    onClick: () -> Unit
) {
    IconButton(
        enabled = !isLoading,
        onClick = {
            if (isSignedIn) {
                onClick()
            } else {
                onShowNotLoggedInDialogBox()
            }
        }) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isLoading) Color.Gray else MaterialTheme.colorScheme.primary
        )
    }
}


private fun favoriteIcon(isSignedIn: Boolean, isFavorite: Boolean): ImageVector =
    if (isSignedIn) {
        if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    } else {
        Icons.Outlined.FavoriteBorder
    }

private fun ratedIcon(isSignedIn: Boolean, isRated: Boolean): ImageVector =
    if (isSignedIn) {
        if (isRated) Icons.Filled.Star else Icons.Outlined.StarBorder
    } else {
        Icons.Outlined.StarBorder
    }

private fun watchlistIcon(isSignedIn: Boolean, isWatchlist: Boolean): ImageVector =
    if (isSignedIn) {
        if (isWatchlist) Icons.Filled.Bookmark else Icons.Outlined.BookmarkBorder
    } else {
        Icons.Outlined.BookmarkBorder
    }