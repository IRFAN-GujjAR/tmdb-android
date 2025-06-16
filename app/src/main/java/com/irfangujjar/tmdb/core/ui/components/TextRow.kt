package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.ScreenPadding

@Composable
fun TextRow(title: String, onSeeAllClick: (() -> Unit)? = null) {
    if (onSeeAllClick == null)
        Text(
            title, fontSize = 17.sp, fontWeight = FontWeight.W500,
            modifier = Modifier.padding(
                start = ScreenPadding.getStartPadding(),
                end = ScreenPadding.getEndPadding(),
                bottom = 12.dp
            )
        )
    else
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())
        ) {
            Text(
                title,
                fontSize = 17.sp,
                fontWeight = FontWeight.W500
            )
            Spacer(modifier = Modifier.weight(1f)) // pushes the button to the end
            TextButton(onClick = onSeeAllClick) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        "See all",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                        contentDescription = "See All $title",
                        modifier = Modifier.size(14.dp),
                        tint = Color.Gray
                    )
                }
            }
        }
}