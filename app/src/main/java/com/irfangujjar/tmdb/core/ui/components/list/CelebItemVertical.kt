package com.irfangujjar.tmdb.core.ui.components.list

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemVerticalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.ProfileSizes


@Composable
fun CelebItemVertical(
    preview: Boolean, values: CelebItemVerticalValues,
    onItemTapped: (Int, String) -> Unit
) {
    Row(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onItemTapped(values.id, values.name)
                })
            }
            .padding(end = 24.dp)
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = MediaImageType.Celebrity,
            imageUrl = values.profilePath,
            width = 70.dp,
            height = 105.dp,
            contentScale = ContentScale.Crop,
            placeHolderSize = 65.dp,
            profileSize = ProfileSizes.w185
        )
        Column(
            modifier = Modifier
                .width(250.dp)
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 12.dp
                )
        ) {
            Text(
                values.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.W500,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (!values.knownFor.isNullOrBlank())
                Text(
                    values.knownFor,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(top = 6.dp)
                )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .size(18.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}


@Preview
@Composable
private fun CelebItemVerticalPreview() {
    TMDbTheme {
        Surface {
            CelebItemVertical(
                preview = true,
                values = CelebItemVerticalValues.dummyData(),
                onItemTapped = {id,name->}
            )
        }
    }
}