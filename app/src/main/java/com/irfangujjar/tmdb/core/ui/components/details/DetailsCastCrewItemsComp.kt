package com.irfangujjar.tmdb.core.ui.components.details

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes


@Composable
fun DetailsCastCrewItemsComp(
    preview: Boolean, credits: CreditsModel,
    onSeeAllClicked: () -> Unit,
    onItemTapped: (Int, String) -> Unit
) {
    val castItems = credits.cast
    val crewItems = credits.crew
    val length = if (castItems.isNotEmpty()) {
        if (castItems.size <= 15) castItems.size else 15
    } else {
        if (crewItems.size <= 15) crewItems.size else 15
    }
    val showSeeAll = length >= 5

    Column {
        CustomDivider(
            topPadding = DividerTopPadding.OneAndHalf,
            bottomPadding = if (showSeeAll) DividerBottomPadding.Half else
                DividerBottomPadding.OneAndHalf
        )
        TextRow(
            title = "Cast & Crew", onSeeAllTapped = if (length < 5) null else onSeeAllClicked
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            items(length) { index ->
                val id: Int
                val profilePath: String?
                val name: String
                val character: String?
                if (castItems.isNotEmpty()) {
                    val castItem = castItems[index]
                    id = castItem.id
                    profilePath = castItem.profilePath
                    name = castItem.name
                    character = castItem.character
                } else {
                    val crewItem = crewItems[index]
                    id = crewItem.id
                    profilePath = crewItem.profilePath
                    name = crewItem.name
                    character = crewItem.job ?: crewItem.department
                }
                Column(
                    modifier = Modifier
                        .width(102.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                onItemTapped(id, name)
                            })
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.Celebrity,
                        imageUrl = profilePath,
                        width = 92.dp,
                        height = 92.dp,
                        contentScale = ContentScale.Crop,
                        borderRadius = 92.dp,
                        placeHolderSize = 50.dp,
                        celebrityPlaceHolderCircularShape = true,
                        profileSize = ProfileSizes.w185
                    )
                    Text(
                        name,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        character,
                        fontSize = 11.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun DetailsCastCrewItemsCompPreview() {
    TMDbTheme {
        Surface {
            DetailsCastCrewItemsComp(
                preview = true,
                credits = CreditsModel.dummyData(type = MediaType.Movie),
                onSeeAllClicked = {},
                onItemTapped = { id, name -> }
            )
        }
    }
}