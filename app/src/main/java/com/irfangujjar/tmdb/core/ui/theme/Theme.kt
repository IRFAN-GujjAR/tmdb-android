package com.irfangujjar.tmdb.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

enum class UserTheme(val id: Int) {
    SYSTEM(0),
    LIGHT(1),
    DARK(2);

    companion object {
        fun fromId(value: Int): UserTheme = when (value) {
            0 -> SYSTEM
            1 -> LIGHT
            2 -> DARK
            else -> SYSTEM
        }
    }
}


//surfaceContainer - BottomNavigation Container Color
//onSecondaryContainer - BottomNavigation Item Active Icon Color
//onSurface - BottomNavigation Item Text Color

private val DarkColorScheme = darkColorScheme(
    primary = PRIMARY_COLOR_DARK,
    secondary = SECONDARY_COLOR_DARK,
    background = NAV_BAR_COLOR_DARK,
    surface = BACKGROUND_COLOR_DARK,
    surfaceContainer = NAV_BAR_COLOR_DARK,
    onSecondaryContainer = PRIMARY_COLOR_DARK,
    onSurface = PRIMARY_COLOR_DARK
//    onSurfaceVariant = Color.Red

)

private val LightColorScheme = darkColorScheme(
    primary = PRIMARY_COLOR,
    secondary = SECONDARY_COLOR,
    background = NAV_BAR_COLOR,
    surface = BACKGROUND_COLOR,
    surfaceContainer = NAV_BAR_COLOR, // BottomNavigation Container Color
    onSecondaryContainer = PRIMARY_COLOR,
    onSurface = PRIMARY_COLOR
//    onSurfaceVariant = Color.Red,
//    onSurface = Color.Blue

//        onPrimary = Color.White,
//    primary = Purple40,
//    secondary = PurpleGrey40,
//    tertiary = Pink40,
//    surface = Color.Blue

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun TMDbTheme(
    userTheme: UserTheme = UserTheme.SYSTEM,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    var isDarkTheme = userTheme == UserTheme.DARK
    if (userTheme == UserTheme.SYSTEM) {
        isDarkTheme = isSystemInDarkTheme()
    }

    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
        isDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}