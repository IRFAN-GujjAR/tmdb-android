package com.irfangujjar.tmdb.core.navigation.nav_keys

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface BottomNavKey : NavKey {
    val index: Int
    val label: String
    val icon: ImageVector

    @Serializable
    data object MoviesNavKey : BottomNavKey {
        override val index: Int = 0
        override val label: String = "Movies"
        override val icon: ImageVector = Icons.Default.Movie
    }

    @Serializable
    data object TVShowsNavKey : BottomNavKey {
        override val index: Int = 1
        override val label: String = "Tv Shows"
        override val icon: ImageVector = Icons.Default.Tv
    }

    @Serializable
    data object CelebsNavKey : BottomNavKey {
        override val index: Int = 2
        override val label: String = "Celebrities"
        override val icon: ImageVector = Icons.Default.Person
    }

    @Serializable
    data object SearchNavKey : BottomNavKey {
        override val index: Int = 3
        override val label: String = "Search"
        override val icon: ImageVector = Icons.Default.Search
    }

    @Serializable
    data object TMDBNavKey : BottomNavKey {
        override val index: Int = 4
        override val label: String = "TMDb"
        override val icon: ImageVector = Icons.Default.AccountBox
    }

    companion object {
        val items = listOf(MoviesNavKey, TVShowsNavKey, CelebsNavKey, SearchNavKey, TMDBNavKey)

        val bottomKeySaver = Saver<BottomNavKey, String>(
            save = { it::class.qualifiedName },
            restore = { classQualifiedName ->
                items.firstOrNull { it::class.qualifiedName == classQualifiedName } ?: MoviesNavKey
            }
        )
    }
}