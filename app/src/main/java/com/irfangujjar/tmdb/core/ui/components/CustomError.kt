package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.error.ErrorEntity
import com.irfangujjar.tmdb.core.error.ErrorType
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.theme.presentation.viewmodel.UserThemeViewModel

@Composable
fun CustomError(
    error: ErrorEntity,
    userTheme: UserTheme,
    onRetry:()-> Unit
) {
    val illustration = getIllustration(error, userTheme, isSystemInDarkTheme = isSystemInDarkTheme())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = ScreenPadding.getHorizontalPadding()
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(illustration),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            error.message,
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        TextButton(onClick = onRetry) {
            Text("RETRY")
        }
    }

}

private fun getIllustration(
    error: ErrorEntity,
    userTheme: UserTheme,
    isSystemInDarkTheme: Boolean
): Int = when (error.type) {
    ErrorType.Network ->
        when (userTheme) {
            UserTheme.SYSTEM -> if (isSystemInDarkTheme) R.drawable.internet_connection_error_dark else R.drawable.internet_connection_error_light
            UserTheme.LIGHT -> R.drawable.internet_connection_error_light
            UserTheme.DARK -> R.drawable.internet_connection_error_dark
        }

    ErrorType.Server ->
        when (userTheme) {
            UserTheme.SYSTEM -> if (isSystemInDarkTheme) R.drawable.server_error_dark else R.drawable.server_error_light
            UserTheme.LIGHT -> R.drawable.server_error_light
            UserTheme.DARK -> R.drawable.server_error_dark
        }

    is ErrorType.Unknown ->
        when (userTheme) {
            UserTheme.SYSTEM -> if (isSystemInDarkTheme) R.drawable.unknown_error_dark else R.drawable.unknown_error_light
            UserTheme.LIGHT -> R.drawable.unknown_error_light
            UserTheme.DARK -> R.drawable.unknown_error_dark
        }
}


@Preview
@Composable
private fun CustomErrorPreview() {
    TMDbTheme {
        Surface {
            CustomError(
                error = ErrorEntity(
                    type = ErrorType.Network,
                    message = "Please Check your internet connection"
                ),
                userTheme = UserTheme.LIGHT,
                onRetry = {}
            )
        }
    }
}
