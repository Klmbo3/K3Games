package com.k3.games.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val K3DarkColorScheme = darkColorScheme(
    primary = K3Gold,
    secondary = K3Red,
    tertiary = K3Orange,
    background = K3Background,
    surface = K3Panel,
    onPrimary = K3Background,
    onSecondary = K3TextPrimary,
    onTertiary = K3Background,
    onBackground = K3TextPrimary,
    onSurface = K3TextPrimary,
)

@Composable
fun K3GamesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = K3DarkColorScheme,
        content = content,
    )
}
