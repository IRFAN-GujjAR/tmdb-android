package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes


@Composable
fun CelebItemHorizontal(preview: Boolean = false, values: CelebItemHorizontalValues) {
    Column(
        modifier = Modifier
            .width(values.config.listItemWidth)
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = MediaImageType.Celebrity,
            imageUrl = values.profilePath,
            width = values.config.listItemWidth,
            height = values.config.imageHeight,
            contentScale = ContentScale.Crop,
            borderRadius = 10.dp,
            placeHolderSize = 85.dp,
            profileSize = values.config.profileSize
        )
        Text(values.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.W500,
            fontSize = values.config.font,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(top = 4.dp))
        if(!values.knownFor.isNullOrBlank())
            Text(values.knownFor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.W500,
                color = Color.Gray,
                fontSize = 11.sp,
                textAlign = TextAlign.Start)
    }
}


@Preview
@Composable
private fun CelebHorizontalPreview() {
    TMDbTheme {
        Surface {
            CelebItemHorizontal(
                preview = true,
                values = CelebItemHorizontalValues.dummyData()
            )
        }
    }
}