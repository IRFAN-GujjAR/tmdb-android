package com.irfangujjar.tmdb.core.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme


@Composable
fun CustomRating(
    voteAverage: Double,
    voteCount: Int,
    iconSize: Dp = 15.dp,
    fontSize: TextUnit = 11.sp,
    voteCountPadding: Dp = 5.dp,
    padding: PaddingValues = PaddingValues(),
    showVoteCount: Boolean = true
) {
    val stars = getStars(voteAverage)

    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(padding)
    ) {
        items(stars) { star ->
            Icon(
                imageVector = star, contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(iconSize)
            )
        }
        if (showVoteCount)
            item {
                Text(
                    text = "( $voteCount )",
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(start = voteCountPadding)
                )
            }
    }
}

private fun getStars(voteAverage: Double): List<ImageVector> {
    val stars = mutableListOf<ImageVector>()

    //Converting scale of 10 to 5
    val rating: Double = voteAverage / 2
    val counter = rating.toInt()
    repeat(counter) {
        stars.add(Icons.Default.Star)
    }
    if (voteAverage - counter > 0) {
        stars.add(Icons.AutoMirrored.Default.StarHalf)
    }

    while (stars.size < 5) {
        stars.add(Icons.Default.StarBorder)
    }
    return stars
}

@Preview
@Composable
private fun CustomRatingPreview() {
    TMDbTheme {
        Surface {
            CustomRating(
                voteAverage = 7.56,
                voteCount = 467
            )
        }
    }
}