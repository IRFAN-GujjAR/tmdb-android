package com.irfangujjar.tmdb.features.main.home.presentation.screens.components

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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen


@Composable
fun CustomNavBar(navController: NavHostController) {

    val currentBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry.value?.destination?.route
    val hiddenRoutes = listOf<String>(
        HomeScreen.Login.route
    )
    val selectedIndex = remember { mutableIntStateOf(0) }

    val selectedScreen = BottomNavBarScreen.items.find { it.route == currentDestination }
    if (selectedScreen != null) {
        selectedIndex.intValue = selectedScreen.index
    }

    val isVisible = currentDestination !in hiddenRoutes

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut()
    ) {

        NavigationBar {
            BottomNavBarScreen.items.forEach { screen ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIconColor = Color.Black.copy(alpha = 0.8f)
                    ),
                    selected = screen.index == selectedIndex.intValue,
                    onClick = {
                        selectedIndex.intValue = screen.index
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.label
                        )
                    },
                    label = { Text(screen.label) },
                )
            }
        }
    }
}