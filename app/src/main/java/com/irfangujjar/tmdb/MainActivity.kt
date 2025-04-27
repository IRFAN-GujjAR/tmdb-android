package com.irfangujjar.tmdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.irfangujjar.tmdb.ui.theme.TMDbTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen=installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TMDbTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UpdateButton(
                        name = "TMDb",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun UpdateButton(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize() // Take up the full screen
    ) {
        Button(
            onClick = { /* Handle click */ },
            modifier = modifier.align(Alignment.Center) // Align the button to the center
        ) {
            Text("Hello $name")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TMDbTheme {
        UpdateButton("TMDb")
    }
}