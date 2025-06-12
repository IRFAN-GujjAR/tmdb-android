package com.irfangujjar.tmdb

import HomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.irfangujjar.tmdb.core.navigation.nav_keys.RootNavKey
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.app_startup.presentation.viewmodel.AppStartupViewModel
import com.irfangujjar.tmdb.features.login.presentation.screens.LoginScreen
import com.irfangujjar.tmdb.features.theme.presentation.viewmodel.UserThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val appStartupViewModel: AppStartupViewModel by viewModels<AppStartupViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition {
            appStartupViewModel.isLoading.value
        }
        super.onCreate(savedInstanceState)
        val statusBarStyle =
            SystemBarStyle.dark(Color.Transparent.toArgb())
        //if you to make the  status bar icons color dark, then use
//        val statusBarStyle= SystemBarStyle.light(Color.Transparent.toArgb(),Color.White.toArgb())

        enableEdgeToEdge(
            statusBarStyle = statusBarStyle
        )

        setContent {

            if (!appStartupViewModel.isLoading.value) {

                val userThemeViewModel = hiltViewModel<UserThemeViewModel>()
                val userTheme = userThemeViewModel.userTheme.collectAsState()
                val userThemeValue = userTheme.value ?: appStartupViewModel.userTheme.value

                val navController = rememberNavController()

                val isAppStartedFirstTime = appStartupViewModel.isAppStartedFirstTime.value

                TMDbTheme(userTheme = userThemeValue) {
//                    AppNavGraph(
//                        navController = navController,
//                        startDestination = if (isAppStartedFirstTime)
//                            AppScreen.Login else
//                            AppScreen.Main,
//                        isAppStartedFirstTime = isAppStartedFirstTime,
//                        userTheme = userThemeValue
//                    )
                    RootNavDisplay(
                        isAppStartedFirstTime = isAppStartedFirstTime,
                        userTheme = userThemeValue
                    )
                }
            }
        }
    }
}

@Composable
private fun RootNavDisplay(
    isAppStartedFirstTime: Boolean,
    userTheme: UserTheme
) {
    if (isAppStartedFirstTime) {
        val backStack =
            rememberNavBackStack(RootNavKey.LoginNavKey)

        NavDisplay(
            backStack = backStack,
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<RootNavKey.LoginNavKey> {
                    LoginScreen(
                        isAppStartedFirstTime = isAppStartedFirstTime,
                        showBackStack = false,
                        navigateToMainScreen = {
                            backStack.removeLastOrNull()
                            backStack.add(RootNavKey.HomeNavKey)
                        }
                    )
                }
                entry<RootNavKey.HomeNavKey> {
                    HomeScreen(
                        userTheme = userTheme
                    )
                }
            }
        )
    } else {
        HomeScreen(
            userTheme = userTheme
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginScreen()
}