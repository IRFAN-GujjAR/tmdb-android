import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.irfangujjar.tmdb.core.navigation.graphs.HomeNavGraph
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.CustomNavBar


@Composable
fun HomeScreen(
    userTheme: UserTheme,
//    navigateToLogin: () -> Unit
) {
    val navController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            CustomNavBar(navController = navController)
        }
    ) { paddingValues ->
        HomeNavGraph(
            navController = navController,
            startDestination = BottomNavBarScreen.Movies,
            outerPadding = paddingValues,
            userTheme = userTheme,
            snackbarHostState = snackBarHostState,
        )
    }

}
