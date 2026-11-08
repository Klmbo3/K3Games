package com.k3.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.k3.games.ui.launcher.GameLauncherScreen
import com.k3.games.ui.theme.K3GamesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            K3GamesTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    GameLauncherScreen()
                }
            }
        }
    }
}
