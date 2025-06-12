import androidx.activity.compose.BackHandler
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.irfangujjar.tmdb.core.navigation.nav_keys.BottomNavKey
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.entries.HomeScreenEntries
import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.nav_bar.CustomNavBar
import com.irfangujjar.tmdb.features.main.home.presentation.screens.components.onBackStackPressed


@Composable
fun HomeScreen(
    userTheme: UserTheme,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val currentKey = rememberSaveable(stateSaver = BottomNavKey.bottomKeySaver) {
        mutableStateOf<BottomNavKey>(BottomNavKey.MoviesNavKey)
    }

    val moviesBackStack = rememberNavBackStack(BottomNavKey.MoviesNavKey)
    val tvShowsBackStack = rememberNavBackStack(BottomNavKey.TVShowsNavKey)
    val celebsBackStack = rememberNavBackStack(BottomNavKey.CelebsNavKey)
    val searchBackStack = rememberNavBackStack(BottomNavKey.SearchNavKey)
    val tmdbBackStack = rememberNavBackStack(BottomNavKey.TMDBNavKey)
    val currentBackStack = when (currentKey.value) {
        BottomNavKey.MoviesNavKey -> moviesBackStack
        BottomNavKey.TVShowsNavKey -> tvShowsBackStack
        BottomNavKey.CelebsNavKey -> celebsBackStack
        BottomNavKey.SearchNavKey -> searchBackStack
        BottomNavKey.TMDBNavKey -> tmdbBackStack
    }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            CustomNavBar(
                currentKey = currentKey.value,
                currentTopKey = currentBackStack.last(),
                onBottomNavItemClicked = {
                    currentKey.value = it
                })
        }
    ) { outerPadding ->
        NavDisplay(
            backStack = currentBackStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                HomeScreenEntries(
                    outerPadding = outerPadding,
                    userTheme = userTheme,
                    snackBarHostState = snackBarHostState,
                    moviesBackStack = moviesBackStack,
                    tvShowsBackStack = tvShowsBackStack,
                    celebsBackStack = celebsBackStack,
                    currentKey = currentKey,
                    searchBackStack = searchBackStack,
                    tmdbBackStack = tmdbBackStack
                )
            }
        )
    }

    BackHandler(enabled = true) {
        onBackStackPressed(
            currentKey = currentKey,
            moviesBackStack = moviesBackStack,
            tvShowsBackStack = tvShowsBackStack,
            celebsBackStack = celebsBackStack,
            searchBackStack = searchBackStack,
            tmdbBackStack = tmdbBackStack,
        )
    }
}






