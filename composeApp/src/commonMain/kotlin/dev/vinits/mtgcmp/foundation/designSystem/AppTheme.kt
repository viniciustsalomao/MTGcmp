package dev.vinits.mtgcmp.foundation.designSystem

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkScheme = darkColorScheme(
    primary = Color(0xE02D78),
    onPrimary = Color(0xFFFFFFFF),
    background = Color(0xFF121215),
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF1a1a1d),
    onSurface = Color(0xFF7b7b82),
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = darkScheme,
        typography = typography
    ) {
        content()
    }
}