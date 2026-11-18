package com.fadi.excercice_dev_movile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fadi.excercice_dev_movile.ui.theme.ExcercicedevmovileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExcercicedevmovileTheme {
                EventListScreen(campusEvents)
            }
        }
    }
}
