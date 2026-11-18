package com.fadi.excercice_dev_movile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.view.WindowCompat
import com.fadi.excercice_dev_movile.ui.theme.ExcercicedevmovileTheme

private const val DARK_THEME_PREFERENCE = "dark_theme"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val preferences = getPreferences(MODE_PRIVATE)
        setContent {
            var darkThemeOverride by remember {
                mutableStateOf(
                    if (preferences.contains(DARK_THEME_PREFERENCE)) {
                        preferences.getBoolean(DARK_THEME_PREFERENCE, false)
                    } else {
                        null
                    }
                )
            }
            val darkTheme = darkThemeOverride ?: isSystemInDarkTheme()

            SideEffect {
                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = !darkTheme
                    isAppearanceLightNavigationBars = !darkTheme
                }
            }

            ExcercicedevmovileTheme(darkTheme = darkTheme) {
                EventListScreen(
                    events = campusEvents,
                    darkTheme = darkTheme,
                    onToggleTheme = {
                        val nextTheme = !darkTheme
                        darkThemeOverride = nextTheme
                        preferences.edit().putBoolean(DARK_THEME_PREFERENCE, nextTheme).apply()
                    }
                )
            }
        }
    }
}
