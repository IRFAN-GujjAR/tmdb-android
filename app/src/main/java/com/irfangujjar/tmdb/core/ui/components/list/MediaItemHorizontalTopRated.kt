package com.irfangujjar.tmdb.core.ui.components.list

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.irfangujjar.tmdb.core.ui.components.image.CustomNetworkImage
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.BackdropSizes
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.core.ui.util.PosterSizes
import com.irfangujjar.tmdb.core.ui.util.getMovieGenres
import com.irfangujjar.tmdb.core.ui.util.getTvShowsGenres
import com.irfangujjar.tmdb.core.ui.util.imageType
import com.irfangujjar.tmdb.core.ui.util.isMovie
import com.irfangujjar.tmdb.features.main.movies.domain.models.MovieModel
import com.irfangujjar.tmdb.features.main.tv_shows.domain.entities.TvShowEntity

@Composable
fun MediaItemHorizontalTopRated(
    preview: Boolean = false,
    type: MediaType,
    movie: MovieModel?,
    tvShow: TvShowEntity?
) {

    val posterPath: String?
    val title: String
    val genreIds: List<Int>
    when (type) {
        MediaType.Movie -> {
            posterPath = movie!!.posterPath
            title = movie.title
            genreIds = movie.genreIds
        }

        MediaType.TvShow -> {
            posterPath = tvShow!!.posterPath
            title = tvShow.name
            genreIds = tvShow.genreIds
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CustomNetworkImage(
            preview = preview,
            isLandscape = false,
            type = type.imageType(),
            imageUrl = posterPath,
            width = 40.dp,
            height = 40.dp,
            contentScale = ContentScale.Crop,
            placeHolderSize = 24.dp,
            posterSize = PosterSizes.w92,
            backdropSize = BackdropSizes.w300,
        )
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .width(210.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.W500
            )
            Text(
                if (type.isMovie()) getMovieGenres(genreIds) else getTvShowsGenres(genreIds),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 11.sp,
                textAlign = TextAlign.Start,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowForwardIos,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Color.Gray
        )
    }
}


@Preview
@Composable
private fun MediaItemHorizontalTopRatedPreview() {
    TMDbTheme {
        Surface {
            MediaItemHorizontalTopRated(
                preview = true,
                type = MediaType.Movie,
                movie = MovieModel.dummyData(),
                tvShow = null
            )
        }
    }
}
