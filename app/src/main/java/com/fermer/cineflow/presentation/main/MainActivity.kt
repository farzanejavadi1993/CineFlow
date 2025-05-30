package com.fermer.cineflow.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.fermer.cineflow.theme.CineFlowTheme
import com.fermer.common.theme.ThemePreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeManager: ThemePreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkMode by themeManager.isDarkMode.collectAsState(initial = false)

            CineFlowTheme(darkTheme = isDarkMode) {
                AppNavHost(themeManager = themeManager)
            }
        }
    }
}




