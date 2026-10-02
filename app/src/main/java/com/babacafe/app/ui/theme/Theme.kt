package com.babacafe.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = BloomCream200,
    onPrimary = BloomBlue500,
    primaryContainer = BloomBlue400,
    onPrimaryContainer = BloomCream100,
    secondary = BloomPollen500,
    onSecondary = BloomInk900,
    background = BloomBlue500,
    onBackground = BloomCream200,
    surface = BloomBlue500,
    onSurface = BloomCream200,
    surfaceVariant = BloomBlue700,
    onSurfaceVariant = BloomCream100,
    outline = BloomCream200
)

@Composable
fun BloomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val bloomColors = BloomColors()
    val bloomTypography = BloomTypography()
    val bloomSpacing = BloomSpacing()

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            if (window != null) {
                window.statusBarColor = BloomBlue500.toArgb()
                window.navigationBarColor = BloomBlue700.toArgb()
                WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
                WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
            }
        }
    }

    CompositionLocalProvider(
        LocalBloomColors provides bloomColors,
        LocalBloomTypography provides bloomTypography,
        LocalBloomSpacing provides bloomSpacing
    ) {
        MaterialTheme(
            colorScheme = LightColorScheme,
            content = content
        )
    }
}

object BloomTheme {
    val colors: BloomColors
        @Composable
        @ReadOnlyComposable
        get() = LocalBloomColors.current

    val typography: BloomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBloomTypography.current

    val spacing: BloomSpacing
        @Composable
        @ReadOnlyComposable
        get() = LocalBloomSpacing.current
}
