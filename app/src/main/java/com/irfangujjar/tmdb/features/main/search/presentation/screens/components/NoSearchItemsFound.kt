package com.irfangujjar.tmdb.features.main.search.presentation.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.R
import com.irfangujjar.tmdb.core.ui.theme.UserTheme


@Composable
fun NoSearchItemsFound(userTheme: UserTheme) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                searchResultsNotFoundIllustration(
                    userTheme = userTheme,
                    isSystemInDarkTheme = isSystemInDarkTheme()
                )
            ),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            "No results found!",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

private fun searchResultsNotFoundIllustration(
    userTheme: UserTheme,
    isSystemInDarkTheme: Boolean
): Int {
    return when (userTheme) {
        UserTheme.SYSTEM -> if (isSystemInDarkTheme) R.drawable.search_results_not_found_dark else
            R.drawable.search_results_not_found_light

        UserTheme.LIGHT -> R.drawable.search_results_not_found_light
        UserTheme.DARK -> R.drawable.search_results_not_found_dark
    }
}

