package com.fermer.cineflow.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.fermer.cineflow.R
import com.fermer.common.theme.ThemePreferenceManager
import com.fermer.trending.presentation.TrendingScreen
import com.fermer.watchlist.presentation.WatchlistScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    themeManager: ThemePreferenceManager
) {
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Trending", "Watchlist")
    val scope = rememberCoroutineScope()
    val isDark by themeManager.isDarkMode.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = {
                        scope.launch { themeManager.toggleDarkMode(!isDark) }
                    }) {
                        Icon(
                            imageVector = if (isDark) Icons.Filled.LightMode
                            else Icons.Filled.DarkMode,
                            contentDescription = stringResource(R.string.toggle_theme)
                        )
                    }
                }
            )
        },
        bottomBar = {
            TabRow(
                selectedTabIndex = selectedTab,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                0 -> TrendingScreen(navController = navController)
                1 -> WatchlistScreen(navController = navController)
            }
        }
    }
}

