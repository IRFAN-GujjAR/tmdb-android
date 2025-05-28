package com.irfangujjar.tmdb.features.main.home.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen


@Composable
fun CustomNavBar(navController: NavHostController) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    NavigationBar {
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStackEntry.value?.destination?.route
        BottomNavBarScreen.items.forEach { screen ->
            NavigationBarItem(
                selected = currentDestination == screen.route ||
                        screen.index == selectedIndex.intValue,
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
                label = { Text(screen.label) }
            )
        }
    }
}