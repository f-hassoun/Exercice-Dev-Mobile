package com.fadi.excercice_dev_movile.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = CampusGreenDark,
    onPrimary = Color(0xFF003826),
    secondaryContainer = Color(0xFF304C40),
    onSecondaryContainer = Color(0xFFCBE9DA),
    surface = Color(0xFF101612),
    onSurface = Color(0xFFE0E8E1),
    surfaceContainer = Color(0xFF1C2520),
    surfaceContainerHighest = Color(0xFF292F2B),
    onSurfaceVariant = Color(0xFFBECBC2)
)

private val LightColorScheme = lightColorScheme(
    primary = CampusGreen,
    onPrimary = Color.White,
    secondaryContainer = Color(0xFFDBEEE2),
    onSecondaryContainer = Color(0xFF1E4939),
    surface = Color(0xFFFAFCF9),
    onSurface = Color(0xFF18231D),
    surfaceContainer = Color(0xFFEAF1EA),
    surfaceContainerHighest = Color(0xFFE6EFE7),
    onSurfaceVariant = Color(0xFF445249)
)

@Composable
fun ExcercicedevmovileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
