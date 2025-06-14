package com.irfangujjar.tmdb.features.main.movies.sub_features.details.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.CollectionModel
import com.irfangujjar.tmdb.core.models.GenreModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaImageType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel


@Composable
fun CollectionComp(
    preview: Boolean,
    collection: CollectionModel,
    genres: List<GenreModel>,
    posterPath: String?,
    backdropPath: String?,
) {
    var genresText = ""
    if (genres.isNotEmpty()) {
        for (i in genres.indices) {
            genresText = if (genresText.isEmpty()) {
                genres[i].name
            } else {
                "$genresText, ${genres[i].name}"
            }
        }
    }

    Column(
        Modifier.padding(top = 12.dp)
    ) {
        CustomDivider()
        TextRow("Collection")
        Row(
            modifier = Modifier.padding(
                start =
                    ScreenPadding.getStartPadding(),
                end = ScreenPadding.getEndPadding(),
                top = 12.dp
            )
        ) {
            CustomNetworkImage(
                preview = preview,
                isLandscape = false,
                type = MediaImageType.Movie,
                imageUrl = collection.posterPath ?: posterPath,
                width = 80.dp,
                height = 100.dp,
                contentScale = ContentScale.Crop,
                borderRadius = 0.dp,
                placeHolderSize = 60.dp,
                posterSize = PosterSizes.w185,
            )
            Column(modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)) {
                Text(
                    collection.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp
                )
                if (genres.isNotEmpty())
                    Text(
                        genresText,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
            }
            Spacer(modifier = Modifier.weight(0.1f))
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterVertically),
                tint = Color.Gray
            )
        }
    }
}

@Preview
@Composable
private fun CollectionCompPreview() {
    TMDbTheme {
        Surface {
            val movie = MovieDetailsModel.dummyData()
            CollectionComp(
                preview = true,
                collection = movie.collection!!,
                genres = movie.genres,
                posterPath = movie.posterPath,
                backdropPath = movie.backdropPath
            )
        }
    }
}
