package com.irfangujjar.tmdb.features.main.celebs.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.irfangujjar.tmdb.core.ui.components.list.CelebItemsHorizontal
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemHorizontalConfigValues
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsHorizontalValues
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebsListModel


@Composable
fun PopularCelebs(
    preview: Boolean,
    popularCelebs: CelebsListModel
) {
    val size = popularCelebs.celebrities.size
    val firsListLastIndex = (size / 2) - 1
    val firstList = popularCelebs.celebrities.subList(0, firsListLastIndex)
    val secondList = popularCelebs.celebrities.subList(firsListLastIndex + 1, size - 1)

    Column {
        CelebItemsHorizontal(
            preview = preview,
            values = CelebItemsHorizontalValues.fromListValues(
                celebs = firstList,
                config = CelebItemHorizontalConfigValues.fromDefault()
            ),
            title = "Popular"
        ) {

        }
        CelebItemsHorizontal(
            preview = preview,
            values = CelebItemsHorizontalValues.fromListValues(
                celebs = secondList,
                config = CelebItemHorizontalConfigValues.fromDefault()
            ),
        )
    }
}

@Preview
@Composable
private fun PopularCelebsPreview(){
    TMDbTheme {
        Surface {
            PopularCelebs(
                preview = true,
                popularCelebs = CelebsListModel(pageNo = 1, totalPages = 2,
                    celebrities = List(20) { CelebModel.dummyData() })
            )
        }
    }
}