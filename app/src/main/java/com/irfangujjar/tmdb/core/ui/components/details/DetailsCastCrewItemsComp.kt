package com.irfangujjar.tmdb.core.ui.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.CreditsModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes


@Composable
fun DetailsCastCrewItemsComp(preview: Boolean, credits: CreditsModel) {
    val cast = credits.cast
    val length = if (cast.size <= 15) cast.size else 15

    Column {
        Spacer(modifier = Modifier.height(3.dp))
        CustomDivider()
        TextRow(title = "Cast & Crew", onSeeAllClick = {

        })
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            items(length) { index ->
                val castItem = cast[index]
                Column(
                    modifier = Modifier.width(102.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomNetworkImage(
                        preview = preview,
                        isLandscape = false,
                        type = MediaImageType.Celebrity,
                        imageUrl = castItem.profilePath,
                        width = 92.dp,
                        height = 92.dp,
                        contentScale = ContentScale.Crop,
                        borderRadius = 92.dp,
                        placeHolderSize = 50.dp,
                        celebrityPlaceHolderCircularShape = true,
                        profileSize = ProfileSizes.w185
                    )
                    Text(
                        castItem.name,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        castItem.character,
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
                credits = CreditsModel.dummyData(type = MediaType.Movie)
            )
        }
    }
}