package com.irfangujjar.tmdb.core.navigation.graphs

import BodyItem
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.core.navigation.screens.HomeScreen
import com.irfangujjar.tmdb.core.ui.components.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    startDestination: String,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        BottomNavBarScreen.items.forEach { bottomScreen ->
            composable(bottomScreen.route) {
                when (bottomScreen) {
                    BottomNavBarScreen.Movies -> BodyItem("Movies", paddingValues = paddingValues) {
                        navController.navigate(HomeScreen.MovieDetails.route)
                    }

                    BottomNavBarScreen.TVShows -> BodyItem(
                        "Tv Shows",
                        paddingValues = paddingValues
                    ) {

                    }

                    BottomNavBarScreen.Celebs -> BodyItem(
                        "Celebrities",
                        paddingValues = paddingValues
                    ) { }

                    BottomNavBarScreen.Search -> BodyItem(
                        "Search",
                        paddingValues = paddingValues
                    ) { }

                    BottomNavBarScreen.TMDB -> BodyItem(
                        "TMDb",
                        paddingValues = paddingValues
                    ) { }
                }
            }
        }
        composable(HomeScreen.MovieDetails.route) {
            Scaffold(
                topBar = {
                    CustomTopAppBar(
                        title = "Movie Details",
                        navigationIcon = {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                },
            ) { innerPadding ->
                Surface(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Details")
                    }
                }
            }
        }


    }
}