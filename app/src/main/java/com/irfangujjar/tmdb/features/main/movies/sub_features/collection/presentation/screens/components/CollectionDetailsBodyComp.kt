package com.irfangujjar.tmdb.features.main.movies.sub_features.collection.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.irfangujjar.tmdb.core.ui.ScreenPadding
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.movies.sub_features.collection.domain.models.CollectionDetailsModel


@Composable
fun CollectionDetailsBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    collectionDetails: CollectionDetailsModel
) {
    Column(
        modifier = Modifier.padding(
            ScreenPadding.getPadding(
                outerPaddingValues = outerPadding,
                innerPaddingValues = innerPadding,
                includeStartPadding = false,
                includeEndPadding = false,
                includeTopPadding = false
            )
        )
    ) {
    
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
                collectionDetails = CollectionDetailsModel.dummyData()
            )
        }
    }
}