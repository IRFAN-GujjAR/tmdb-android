package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.components.CustomDivider
import com.irfangujjar.tmdb.core.ui.components.DividerBottomPadding
import com.irfangujjar.tmdb.core.ui.components.DividerStartPadding
import com.irfangujjar.tmdb.core.ui.components.DividerTopPadding
import com.irfangujjar.tmdb.core.ui.components.TextRow
import com.irfangujjar.tmdb.core.ui.components.details.DetailsBackdropComp
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemVertical
import com.irfangujjar.tmdb.core.ui.components.list.MediaItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.MediaItemVerticalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.util.MediaType
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel


@Composable
fun CollectionDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    collectionDetails: CollectionDetailsModel,
    onMovieItemTapped:(Int, String, String?, String?)-> Unit
) {
    LazyColumn(
        contentPadding = ScreenPadding.getPadding(
            outerPaddingValues = outerPadding,
            innerPaddingValues = innerPadding,
            includeStartPadding = false,
            includeEndPadding = false,
            includeTopPadding = false
        ),
    ) {
        item {
            CollectionBackdropComp(
                preview = preview,
                name = collectionDetails.name,
                overview = collectionDetails.overview,
                posterPath = collectionDetails.posterPath,
                backdropPath = collectionDetails.backdropPath,
            )
            CustomDivider(topPadding = DividerTopPadding.OneAndHalf,
                bottomPadding = DividerBottomPadding.OneAndHalf)
            TextRow(title = "This Collection includes")
        }
        items(collectionDetails.movies.size) {index->
            val movie=collectionDetails.movies[index]
            Box (modifier = Modifier.padding(horizontal = ScreenPadding.getHorizontalPadding())) {
                MediaItemVertical(
                    preview = preview,
                    values = MediaItemVerticalValues(
                        mediaType = MediaType.Movie,
                        mediaId = movie.id,
                        mediaTitle = movie.title,
                        mediaGenre = movie.genreIds,
                        posterPath = movie.posterPath,
                        backdropPath = movie.backdropPath,
                        voteAverage = movie.voteAverage,
                        voteCount = movie.voteCount
                    ),
                    onItemTapped = onMovieItemTapped
                )
            }
            if (index<collectionDetails.movies.size-1)
                CustomDivider(topPadding = DividerTopPadding.OneAndHalf,
                    bottomPadding = DividerBottomPadding.OneAndHalf)
        }
    }
}

@Preview
@Composable
private fun CollectionDetailsBodyCompPreview() {
    TMDbTheme {
        Surface {
            CollectionDetailsBodyComp(
                preview = true,
                outerPadding = PaddingValues(),
                innerPadding = PaddingValues(),
                collectionDetails = CollectionDetailsModel.dummyData(),
                onMovieItemTapped = {id,title,posterPath,backdropPath->}
            )
        }
    }
}