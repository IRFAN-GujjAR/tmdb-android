package com.irfangujjar.tmdb.features.main.home.presentation.screens.components.nav_bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation3.runtime.NavKey
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey
import com.irfangujjar.tmdb.core.navigation.nav_keys.HomeNavKey


@Composable
fun CustomNavBar(
    currentKey: BottomNavKey,
    currentTopKey: NavKey,
    onBottomNavItemClicked: (BottomNavKey) -> Unit
) {

    val isVisible = showBottomNavBar(currentTopKey = currentTopKey)

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {

        NavigationBar {

            BottomNavKey.items.forEach { key ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = Color.Black.copy(alpha = 0.8f)
                    ),
                    selected = currentKey == key,
                    onClick = {
                        onBottomNavItemClicked(key)
                    },
                    icon = {
                        Icon(
                            imageVector = key.icon,
                            contentDescription = key.label
                        )
                    },
                    label = { Text(key.label) },
                )
            }
        }
    }
}

private fun showBottomNavBar(currentTopKey: NavKey): Boolean {
    return currentTopKey !is HomeNavKey.LoginNavKey && currentTopKey !is HomeNavKey.RateMediaNavKey
}


