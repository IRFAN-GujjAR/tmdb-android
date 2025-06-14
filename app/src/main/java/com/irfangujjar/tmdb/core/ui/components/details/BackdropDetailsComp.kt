package com.irfangujjar.tmdb.core.ui.components.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.models.GenreModel
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomRating
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.imageType
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.movies.sub_features.details.domain.models.MovieDetailsModel


@Composable
fun BackdropDetailsComp(
    preview: Boolean,
    type: MediaType,
    backdropDetailsPath: String?,
    backdropPath: String?,
    posterDetailsPath: String?,
    posterPath: String?,
    title: String,
    voteAverage: Double?,
    voteCount: Int?,
    genres: List<GenreModel>,
    overview: String?
) {
    Box {
        Box {
            Box {
                CustomNetworkImage(
                    preview = preview,
                    isLandscape = true,
                    type = type.imageType(),
                    imageUrl = backdropDetailsPath ?: backdropPath,
                    width = 0.dp,
                    height = 211.dp,
                    contentScale = ContentScale.FillWidth,
                    borderRadius = 0.dp,
                    movieTvBorderDecoration = false,
                    placeHolderSize = 84.dp,
                    backdropSize = BackdropSizes.w780,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(76.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.surface
                                ),
                            )
                        )
                        .align(Alignment.BottomCenter)
                )
            }
            Box(
                modifier = Modifier
                    .align(
                        alignment = Alignment.BottomStart
                    )
                    .padding(
                        top = 202.dp,
                        start = ScreenPadding.getStartPadding()
                    )
            ) {
                CustomNetworkImage(
                    preview = preview,
                    isLandscape = false,
                    type = type.imageType(),
                    imageUrl = posterDetailsPath ?: posterPath,
                    width = 92.dp,
                    height = 136.dp,
                    contentScale = ContentScale.FillWidth,
                    borderRadius = 0.dp,
                    movieTvBorderDecoration = false,
                    placeHolderSize = 60.dp,
                    posterSize = PosterSizes.w185,
                )
            }
        }
        Box(
            modifier = Modifier.padding(
                top = 202.dp,
                start = ScreenPadding.getStartPadding() + 92.dp
            )
        ) {
            InformationComp(
                title = title,
                voteAverage = voteAverage,
                voteCount = voteCount,
                genres = genres,
                overview = overview
            )
        }
    }


}

@Composable
private fun InformationComp(
    title: String,
    voteAverage: Double?,
    voteCount: Int?,
    genres: List<GenreModel>,
    overview: String?
) {
    Column(
        modifier = Modifier.padding(start = 8.dp, top = 2.dp, end = ScreenPadding.getEndPadding())
    ) {
        Text(
            title,
            fontSize = 18.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(end = ScreenPadding.getEndPadding())
        )
        if (voteAverage!=null&&voteCount!=null)
            CustomRating(
                voteAverage = voteAverage,
                voteCount = voteCount,
            )
        if (genres.isNotEmpty())
            FlowRow(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                (genres).forEach { genre ->
                    Box(
                        modifier = Modifier
                            .border(BorderStroke(1.dp, Color.Gray))
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            genre.name,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                }
            }
        if (!overview.isNullOrBlank())
            Text(
                overview,
                fontSize = 12.sp,
                color = Color.Gray,
            )
    }
}


@Preview
@Composable
private fun BackdropDetailsCompPreview() {
    TMDbTheme {
        Surface {
            val movie = MovieModel.dummyData()
            val movieDetails = MovieDetailsModel.dummyData()
            BackdropDetailsComp(
                preview = true,
                type = MediaType.Movie,
                backdropDetailsPath = movieDetails.backdropPath,
                backdropPath = movie.backdropPath,
                posterDetailsPath = movieDetails.posterPath,
                posterPath = movie.posterPath,
                title = movieDetails.title,
                voteAverage = movieDetails.voteAverage,
                voteCount = movieDetails.voteCount,
                genres = movieDetails.genres,
                overview = movieDetails.overview,
            )
        }
    }
}