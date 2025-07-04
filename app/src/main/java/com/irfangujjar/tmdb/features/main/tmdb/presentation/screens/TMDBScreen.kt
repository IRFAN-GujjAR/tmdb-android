package com.irfangujjar.tmdb.features.main.tmdb.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomError
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomMessageDialogBox
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.core.viewmodels.SignInStatusViewModel
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity
import com.irfangujjar.tmdb.features.main.tmdb.presentation.screens.components.TMDBProfile
import com.irfangujjar.tmdb.features.main.tmdb.presentation.viewmodels.TMDBViewModel
import com.irfangujjar.tmdb.features.main.tmdb.presentation.viewmodels.state.AccountDetailsState
import com.irfangujjar.tmdb.features.main.tmdb.sub_features.media_list.data.data_sources.api.TMDBMediaListCategory


@Composable
fun TMDBScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    userTheme: UserTheme,
    snackbarHostState: SnackbarHostState?,
    viewModel: TMDBViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onNavigateToTheme: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToTMDBMediaList: (HomeNavKey.TMDBMediaListNavKey) -> Unit,
    signInStatusViewModel: SignInStatusViewModel
) {
    val state = viewModel.state.collectAsState().value
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        when (state) {
            AccountDetailsState.Loading -> {
                CustomLoading()
            }

            is AccountDetailsState.Empty, is AccountDetailsState.Loaded,
            is AccountDetailsState.ErrorWithCache -> {
                if (state is AccountDetailsState.ErrorWithCache) {
                    if (viewModel.showAlert) {
                        LaunchedEffect(Unit) {
                            snackbarHostState?.showSnackbar(viewModel.alertMessage)
                            viewModel.clearAlert()
                        }
                    }
                }
                var accountDetails: AccountDetailsWithoutIdEntity? = null
                if (state is AccountDetailsState.Loaded)
                    accountDetails = state.accountDetails
                else if (state is AccountDetailsState.ErrorWithCache)
                    accountDetails = state.accountDetails
                TMDbScreenBody(
                    preview = preview,
                    paddingValues = outerPadding,
                    accountDetails = accountDetails,
                    navigateToLogin = onNavigateToLogin,
                    navigateToTheme = onNavigateToTheme,
                    onNavigateToAbout = onNavigateToAbout,
                    onNavigateToTMDBMediaList = {
                        onNavigateToTMDBMediaList(HomeNavKey.TMDBMediaListNavKey(category = it))
                    },
                    onShowNotSignedInDialogBox = {
                        viewModel.showNotSignedInMessage()
                    },
                    onSignOut = {
                        viewModel.signOut(onSuccess = {
                            signInStatusViewModel.changeStatusToSignedOut()
                        })
                    },
                    isSigningOut = viewModel.isSigningOut
                )
            }

            is AccountDetailsState.Error -> {
                CustomError(
                    error = state.error,
                    userTheme = userTheme
                ) {
                    viewModel.retry()
                }
            }
        }
    }

    if (viewModel.showNotSignedInMessage.value)
        CustomMessageDialogBox(
            title = "Not Signed In",
            message = "You need to sign in for accessing this!"
        ) {
            viewModel.hideNotSignedInMessage()
        }

    if (viewModel.isSignOutError) {
        CustomMessageDialogBox(
            title = "SignOut Error",
            message = viewModel.signOutErrorMsg,
            onDismiss = {
                viewModel.resetSignOutError()
            }
        )
    }
}

@Composable
private fun TMDbScreenBody(
    preview: Boolean,
    paddingValues: PaddingValues,
    accountDetails: AccountDetailsWithoutIdEntity?,
    navigateToLogin: () -> Unit,
    navigateToTheme: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToTMDBMediaList: (TMDBMediaListCategory) -> Unit,
    onShowNotSignedInDialogBox: () -> Unit,
    onSignOut: () -> Unit,
    isSigningOut: Boolean
) {
    val scrollState = rememberScrollState()
    Box() {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(
                    ScreenPadding.getPadding(
                        outerPaddingValues = paddingValues,
                        includeStartPadding = false,
                        includeEndPadding = false
                    )
                )
        ) {
            TMDBProfile(
                preview = preview,
                accountDetails = accountDetails
            )
            if (accountDetails == null)
                Button(
                    onClick = navigateToLogin,
                    modifier = Modifier
                        .padding(
                            top = 24.dp, bottom = 12.dp,
                            start = ScreenPadding.getStartPadding(),
                            end = ScreenPadding.getEndPadding()
                        )
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text("Sign In / Sign Up", color = Color.White.copy(0.8f))
                }
            else
                Spacer(modifier = Modifier.height(24.dp))
            ListItem(
                modifier = Modifier
                    .clickable(enabled = !isSigningOut, onClick = {
                        if (accountDetails == null) {
                            onShowNotSignedInDialogBox()
                        } else {
                            onNavigateToTMDBMediaList(TMDBMediaListCategory.Favorite)
                        }
                    }),
                headlineContent = { Text("Favorite") },
                supportingContent = { Text("Your favorites Movies & Tv Shows") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite Icon"
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "Arrow Forward"
                    )
                })
            ListItem(
                modifier = Modifier
                    .clickable(enabled = !isSigningOut, onClick = {
                        if (accountDetails == null) {
                            onShowNotSignedInDialogBox()
                        } else {
                            onNavigateToTMDBMediaList(TMDBMediaListCategory.Watchlist)
                        }
                    }),
                headlineContent = { Text("WatchList") },
                supportingContent = { Text("Movies and TvShows Added to watchlist") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.BookmarkBorder,
                        contentDescription = "Watchlist Icon"
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "Arrow Forward"
                    )
                })
            ListItem(
                modifier = Modifier
                    .clickable(enabled = !isSigningOut, onClick = {
                        if (accountDetails == null) {
                            onShowNotSignedInDialogBox()
                        } else {
                            onNavigateToTMDBMediaList(TMDBMediaListCategory.Rated)
                        }
                    }),
                headlineContent = { Text("Ratings") },
                supportingContent = { Text("Rated Movies & Tv Shows") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.StarOutline,
                        contentDescription = "Rating Icon"
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "Arrow Forward"
                    )
                })
            ListItem(
                modifier = Modifier
                    .clickable(enabled = !isSigningOut, onClick = navigateToTheme),
                headlineContent = { Text("Theme") },
                supportingContent = { Text("Set Light & Dark Theme") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Theme Icon"
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "Arrow Forward"
                    )
                })
            ListItem(
                modifier = Modifier
                    .clickable(enabled = !isSigningOut, onClick = onNavigateToAbout),
                headlineContent = { Text("About") },
                supportingContent = { Text("Information about this app") },
                leadingContent = {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Appearances Icon"
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "Arrow Forward"
                    )
                })
            if (accountDetails != null)
                Button(
                    enabled = !isSigningOut,
                    onClick = onSignOut,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .align(alignment = Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(0.8f))
                ) {
                    Text("Sign Out", color = Color.White.copy(0.7f))
                }
        }
        if (isSigningOut)
            CustomLoading()
    }
}

@Preview
@Composable
private fun TMDBScreenPreview() {
    TMDbTheme {
        Surface {
            TMDbScreenBody(
                preview = true,
                paddingValues = PaddingValues(),
                accountDetails = null,
                navigateToLogin = {},
                navigateToTheme = {},
                onNavigateToAbout = {},
                onNavigateToTMDBMediaList = {},
                onShowNotSignedInDialogBox = {},
                onSignOut = {},
                isSigningOut = false,
            )
        }
    }
}