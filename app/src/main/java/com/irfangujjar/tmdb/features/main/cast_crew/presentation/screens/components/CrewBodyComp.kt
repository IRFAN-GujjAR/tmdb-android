package com.irfangujjar.tmdb.features.main.cast_crew.presentation.screens.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import com.irfangujjar.tmdb.core.models.CrewModel
import com.irfangujjar.tmdb.core.ui.components.list.CelebItemsVerticalList
import com.irfangujjar.tmdb.core.ui.components.list.values.CelebItemsVerticalListValues
import com.irfangujjar.tmdb.features.main.celebs.domain.models.CelebModel


@Composable
fun CrewBodyComp(
    preview: Boolean,
    outerPadding: PaddingValues,
    innerPadding: PaddingValues,
    listState: LazyListState? = null,
    crew: List<CrewModel>,
    onCrewItemTapped: (Int, String) -> Unit
) {

    CelebItemsVerticalList(
        preview = preview,
        state = listState,
        outerPadding = outerPadding,
        innerPadding = innerPadding,
        values = CelebItemsVerticalListValues.fromCelebs(
            celebs = crew.map {
                CelebModel(
                    id = it.id, name = it.name,
                    knownFor = it.job ?: it.department,
                    profilePath = it.profilePath
                )
            }
        ),
        onItemTapped = onCrewItemTapped
    )
}