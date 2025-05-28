import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.irfangujjar.tmdb.core.navigation.graphs.HomeNavGraph
import com.irfangujjar.tmdb.core.navigation.screens.BottomNavBarScreen
import com.irfangujjar.tmdb.features.main.home.presentation.components.CustomNavBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
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

@Composable
fun BodyItem(
    text: String,
    paddingValues: PaddingValues,
    onNavigateClicked: () -> Unit,
) {
    Text("Hello")
//    Scaffold(
//        topBar = {
//            CustomTopAppBar(
//                title = text
//            )
//        },
//    ) { innerPadding ->
//        val padding = PaddingValues(
//            top = innerPadding.calculateTopPadding() + paddingValues.calculateTopPadding(),
//            bottom = innerPadding.calculateBottomPadding() +
//                    paddingValues.calculateBottomPadding()
//        )
//        Surface(modifier = Modifier.fillMaxSize()) {
//            LazyColumn(
//                contentPadding = padding
//            ) {
//                items(100) {
//                    Button(onClick = onNavigateClicked) {
//                        Text(text)
//                    }
//                }
//            }
//        }
//    }

}