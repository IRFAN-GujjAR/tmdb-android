package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.runtime.Composable


@Composable
fun NotSignedInDialogBox(onDismiss: () -> Unit) {
    CustomMessageDialogBox(
        title = "Not Signed In",
        message = "You need to sign in first!",
        onDismiss = onDismiss
    )
}