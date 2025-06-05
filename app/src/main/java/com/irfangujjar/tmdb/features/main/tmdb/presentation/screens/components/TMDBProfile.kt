package com.irfangujjar.tmdb.features.main.tmdb.presentation.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.features.main.tmdb.domain.entities.AccountDetailsWithoutIdEntity

@Composable
fun TMDBProfile(preview: Boolean, accountDetails: AccountDetailsWithoutIdEntity?) {
    if (accountDetails == null)
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = ScreenPadding.getHorizontalPadding())) {
            Icon(
                imageVector = Icons.Default.AccountCircle, contentDescription = "Account Avatar",
                modifier = Modifier.size(60.dp)
            )
        }
    else
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            Profile(preview = preview, profilePath = accountDetails.profilePath)
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                accountDetails.username,
                fontWeight = FontWeight.W500,
                fontSize = 28.sp
            )
        }

}

@Composable
private fun Profile(preview: Boolean, profilePath: String?) {
    if (profilePath == null)
        Icon(
            imageVector = Icons.Default.AccountCircle, contentDescription = "Account Avatar",
            modifier = Modifier.size(40.dp)
        )
    else
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = MediaImageType.Celebrity,
            imageUrl = profilePath,
            width = 45.dp,
            height = 45.dp,
            contentScale = ContentScale.Crop,
            borderRadius = 45.dp,
            placeHolderSize = 45.dp,
            celebrityPlaceHolderCircularShape = true,
            profileSize = ProfileSizes.w45
        )
}

@Preview
@Composable
private fun TMDBProfilePreview() {
    TMDbTheme {
        Surface {
            TMDBProfile(
                preview = true,
                accountDetails = null
//                accountDetails = AccountDetailsWithoutIdEntity(
//                    username = "IRFAN",
//                    profilePath = ""
//                )
            )
        }
    }
}