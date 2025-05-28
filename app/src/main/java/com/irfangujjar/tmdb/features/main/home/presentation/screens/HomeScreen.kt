import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.irfangujjar.tmdb.core.navigation.graphs.HomeNavGraph
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.features.main.home.presentation.components.CustomNavBar


@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            CustomNavBar(navController = navController)
        }
    ) { paddingValues ->
        HomeNavGraph(
            navController = navController,
            startDestination = BottomNavBarScreen.Movies.route,
            paddingValues = paddingValues
        )
    }

}
