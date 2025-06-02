package com.irfangujjar.tmdb

import android.os.Bundle
import android.util.Log
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
import androidx.navigation.compose.rememberNavController
import com.irfangujjar.tmdb.core.navigation.graphs.AppNavGraph
import com.irfangujjar.tmdb.core.navigation.screens.AppScreen
import com.irfangujjar.tmdb.core.ui.theme.TMDbTheme
import com.irfangujjar.tmdb.core.ui.theme.UserTheme
import com.irfangujjar.tmdb.features.app_startup.presentation.viewmodel.AppStartupViewModel
import com.irfangujjar.tmdb.features.login.presentation.ui.screens.LoginScreen
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
                    AppNavGraph(
                        navController = navController,
                        startDestination = if (isAppStartedFirstTime)
                            AppScreen.Login.route else
                            AppScreen.Main.route,
                        isAppStartedFirstTime = isAppStartedFirstTime,
                        userTheme = userThemeValue
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginScreen()
}