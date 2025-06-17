package com.irfangujjar.tmdb.features.main.celebs.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsHorizontalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemsHorizontalListValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.features.main.celebs.sub_features.details.domain.models.CelebDetailsModel


@Composable
fun CelebDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    celebDetails: CelebDetailsModel,
    onMovieItemTapped: (Int, String, String?, String?) -> Unit,
    onTvShowItemTapped: (Int, String, String?, String?) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(
                ScreenPadding.getPadding(
                    outerPaddingValues = outerPadding,
                    innerPaddingValues = innerPadding,
                    includeStartPadding = false,
                    includeEndPadding = false
                )
            )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            CustomNetworkImage(
                preview = preview,
                isLandscape = false,
                type = MediaImageType.Celebrity,
                imageUrl = celebDetails.profilePath,
                width = 130.dp,
                height = 194.dp,
                contentScale = ContentScale.Crop,
                borderRadius = 8.dp,
                celebrityPlaceHolderCircularShape = false,
                placeHolderSize = 50.dp,
                profileSize = ProfileSizes.w185
            )
            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    celebDetails.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W500
                )
                if (!celebDetails.department.isNullOrBlank())
                    DescriptionComp(title = "Known For", data = "Acting")
                if (!celebDetails.placeOfBirth.isNullOrBlank())
                    DescriptionComp(title = "Place of Birth", data = celebDetails.placeOfBirth)
                if (!celebDetails.dateOfBirth.isNullOrBlank())
                    DescriptionComp(title = "Date of Birth", data = celebDetails.dateOfBirth)
                if (!celebDetails.dateOfDeath.isNullOrBlank())
                    DescriptionComp(title = "Date of Death", data = celebDetails.dateOfDeath)
            }
        }
        if (!celebDetails.biography.isNullOrBlank()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.OneAndHalf
            )
            Text(
                "Biography", fontSize = 18.sp, fontWeight = FontWeight.W500,
                modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())
            )
            Text(
                celebDetails.biography,
                fontSize = 13.sp,
                color = Color.Gray,
                modifier = Modifier.padding(
                    top = 12.dp, start = ScreenPadding.getStartPadding(),
                    end = ScreenPadding.getEndPadding()
                )
            )

        }
        if (celebDetails.isMovieCreditsPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromMovies(
                    movies = celebDetails.movieCreditsMovies(),
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.fromDefault()
                ),
                title = "Movies",
                onSeeAllClick = {},
                onItemTapped = onMovieItemTapped
            )
        }
        if (celebDetails.isTvCreditsPresent()) {
            CustomDivider(
                topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.Half
            )
            MediaItemsHorizontalList(
                preview = preview,
                values = MediaItemsHorizontalListValues.fromTvShows(
                    tvShows = celebDetails.tvCreditsTvShows(),
                    isLandscape = false,
                    configValues = MediaItemsHorizontalListConfigValues.fromDefault()
                ),
                title = "Tv Shows",
                onSeeAllClick = {},
                onItemTapped = onTvShowItemTapped
            )
        }

    }
}

@Composable
private fun DescriptionComp(title: String, data: String) {
    Spacer(modifier = Modifier.height(8.dp))
    Text(title, fontSize = 11.sp)
    Text(
        data, fontSize = 16.sp,
        color = Color.Gray
    )
}

@Preview
@Composable
private fun CelebDetailsBodyPreview() {
    TMDbTheme {
        Surface {
            CelebDetailsBodyComp(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                celebDetails = CelebDetailsModel.dummyData(),
                onMovieItemTapped = { _, _, _, _ -> },
                onTvShowItemTapped = { _, _, _, _ -> }
            )
        }
    }
}