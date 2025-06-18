package com.irfangujjar.tmdb.features.about.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.theme.PRIMARY_COLOR
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme


@Composable
fun AboutScreen(
    outerPadding: PaddingValues,
    onBackStackPressed: () -> Unit,
) {
    Scaffold(topBar = {
        CustomTopAppBar(
            title = "About", showBackStack = true,
            onBackStackPressed = onBackStackPressed
        )
    }) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            AboutScreenBody(outerPadding = outerPadding, innerPadding = innerPadding)
        }
    }
}


@Composable
private fun AboutScreenBody(
    outerPadding: PaddingValues,
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier.padding(
            ScreenPadding.getPadding(
                outerPaddingValues = outerPadding,
                innerPaddingValues = innerPadding
            )
        ).padding(horizontal = ScreenPadding.getHorizontalPadding()/2)
    ) {
        TitleComp("\uD83D\uDD17 Open Source:", topPadding = 0.dp)
        Text(
            text = "This app is built using The Movie Database (TMDb) API and brings you detailed " +
                    "information about your favorite movies, TV shows, and trending content — " +
                    "all in one place.\nThe app uses beautiful illustrations from Storyset, " +
                    "adding a friendly touch to your browsing experience.",
            fontSize = 14.sp,
        )
        TitleComp("\uD83D\uDD17 Open Source:")
        SpannedLinkedComp(info = "This app is open source! Check out the code or contribute on GitHub: ",
            linkText ="github.com/IRFAN-GujjAR/tmdb",
            link = "https://github.com/IRFAN-GujjAR/tmdb")
        TitleComp("\uD83D\uDCCC Credits:", topPadding = 24.dp)
        SpannedLinkedComp(info = "Data Powered by ", linkText ="themoviedb.org",
            link = "https://www.themoviedb.org/")
        SpannedLinkedComp(info = "Illustrations by Storyset: ", linkText ="storyset.com",
            link = "https://storyset.com/")

    }
}

@Composable
private fun TitleComp(title: String,topPadding: Dp = 24.dp){
    Text(title, fontSize = 16.sp, fontWeight = FontWeight.W500,
        modifier = Modifier.padding(top = topPadding, bottom = 4.dp))
}

@Composable
private fun SpannedLinkedComp(info: String,linkText: String,link: String){
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = 14.sp
            )
        ) {
            append(info)
        }
        withLink(
            LinkAnnotation.Url(
                link,
                TextLinkStyles(
                    style = SpanStyle(
                        color = PRIMARY_COLOR,
                        fontSize = 14.sp
                    )
                ),
            )
        ) {
            append(linkText)
        }

    })
}

@Preview
@Composable
private fun AboutScreenBodyPreview() {
    TMDbTheme {
        AboutScreen(outerPadding = PaddingValues()) { }
    }
}