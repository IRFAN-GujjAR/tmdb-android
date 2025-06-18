package com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.irfangujjar.tmdb.core.models.SeasonModel
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.CustomLoading
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.SeeAllSeasonsViewModel
import com.irfangujjar.tmdb.features.main.tv_shows.sub_features.season.presentation.viewmodels.states.SeeAllSeasonsState


@Composable
fun SeeAllSeasonsScreen(
    preview: Boolean = false,
    outerPadding: PaddingValues,
    onBackPressed: () -> Unit,
    key: HomeNavKey.SeeAllSeasonsNavKey,
    viewModel: SeeAllSeasonsViewModel = hiltViewModel(),
    onNavigateToSeasonDetails:(HomeNavKey.SeasonDetailsNavKey)-> Unit
) {
    viewModel.initialize(key)

    val state = viewModel.state.collectAsState().value

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = "Seasons", showBackStack = true,
                onBackStackPressed = onBackPressed
            )
        }
    ) { innerPadding ->
        Surface(modifier = Modifier.fillMaxSize()) {
            when (state) {
                SeeAllSeasonsState.Initializing -> CustomLoading()
                is SeeAllSeasonsState.Initialized -> SeeAllSeasonsBody(
                    preview = preview,
                    outerPadding = outerPadding,
                    innerPadding = innerPadding,
                    key = key,
                    seasons = state.seasons,
                    onNavigateToSeasonDetails = onNavigateToSeasonDetails
                )
            }
        }
    }
}


@Composable
private fun SeeAllSeasonsBody(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    key: HomeNavKey.SeeAllSeasonsNavKey,
    seasons: List<SeasonModel>,
    onNavigateToSeasonDetails:(HomeNavKey.SeasonDetailsNavKey)-> Unit
) {
    LazyColumn(
        contentPadding = ScreenPadding.getPadding(
            outerPaddingValues = outerPadding,
            innerPaddingValues = innerPadding,
            includeEndPadding = false,
            includeStartPadding = false
        )
    ) {
        itemsIndexed(seasons) { index, season ->
            Column {
                Row(
                    modifier = Modifier
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                               onNavigateToSeasonDetails(
                                   HomeNavKey.SeasonDetailsNavKey(
                                       tvId = key.tvId,
                                       tvShowName = key.tvShowName,
                                       seasonName = season.name,
                                       seasonNo = season.seasonNo,
                                       tvShowPosterPath =  key.tvShowPosterPath,
                                       episodeImagePlaceHolder = key.episodeImagePlaceHolder
                                   )
                               )
                            })
                        }
                        .padding(horizontal = ScreenPadding.getHorizontalPadding())
                ) {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.TvShow,
                        imageUrl = season.posterPath ?: key.tvShowPosterPath,
                        width = 63.dp,
                        height = 85.dp,
                        contentScale = ContentScale.Crop,
                        placeHolderSize = 48.dp,
                        posterSize = PosterSizes.w92,
                    )
                    Text(
                        season.name, fontSize = 16.sp, fontWeight = FontWeight.W500,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp, top = 8.dp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.weight(0.2f))
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier
                            .size(18.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
                if (index < seasons.size - 1)
                    CustomDivider()
            }
        }
    }
}

@Preview
@Composable
private fun SeeAllSeasonsBodyPreview() {
    TMDbTheme {
        Surface {
            SeeAllSeasonsBody(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                key = HomeNavKey.SeeAllSeasonsNavKey(
                    argId = "",
                    tvId = 1,
                    tvShowName = "",
                    tvShowPosterPath = "",
                    episodeImagePlaceHolder = ""
                ),
                seasons = SeasonModel.dummyListData(),
                onNavigateToSeasonDetails = {}
            )
        }
    }
}