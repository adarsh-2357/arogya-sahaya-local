package com.arogyasahaya.local.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun ArogyaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Disabled for consistent accessible palette
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        // Dynamic color disabled for consistent high-contrast experience
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                darkColorScheme().copy(
                    primary = LightColorScheme.primary,
                    onPrimary = LightColorScheme.onPrimary,
                    secondary = LightColorScheme.secondary,
                    error = LightColorScheme.error
                )
            } else {
                lightColorScheme().copy(
                    primary = LightColorScheme.primary,
                    onPrimary = LightColorScheme.onPrimary,
                    secondary = LightColorScheme.secondary,
                    error = LightColorScheme.error
                )
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Set status bar color for better visibility
            window.statusBarColor = colorScheme.primary.toArgb()
            // Ensure status bar icons are visible
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Convenience function for accessing theme colors
object ArogyaColors {
    val primary = DeepTeal
    val onPrimary = OnDeepTeal
    val secondary = DeepOrange
    val onSecondary = OnDeepOrange
    val background = BackgroundLight
    val surface = SurfaceLight
    val error = ErrorLight
    val primaryContainer = PrimaryContainerLight
    val secondaryContainer = SecondaryContainerLight
}
