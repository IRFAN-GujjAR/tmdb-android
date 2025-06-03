package com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel


@Composable
fun TrendingCelebItem(preview: Boolean = false, celeb: CelebModel) {
    Row(
        modifier = Modifier
            .height(80.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = MediaImageType.Celebrity,
            imageUrl = celeb.profilePath,
            width = 70.dp,
            height = 70.dp,
            contentScale = ContentScale.Crop,
            borderRadius = 70.dp,
            placeHolderSize = 45.dp,
            celebrityPlaceHolderCircularShape = true,
            profileSize = ProfileSizes.w92
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .width(194.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                celeb.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W500
            )
            if (celeb.knownFor != null)
                Text(
                    celeb.knownFor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    fontWeight = FontWeight.W500
                )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Color.Gray,
        )
    }
}

@Preview
@Composable
private fun TrendingCelebItemPreview() {
    TMDbTheme {
        Surface {
            TrendingCelebItem(
                preview = true,
                celeb = CelebModel.dummyData()
            )
        }
    }
}
