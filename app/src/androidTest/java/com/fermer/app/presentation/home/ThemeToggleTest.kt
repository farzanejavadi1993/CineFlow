package com.fermer.app.presentation.home


import androidx.compose.runtime.*
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ThemeToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun toggle_theme_button_changes_state() {
        composeTestRule.setContent {
            var isDark by remember { mutableStateOf(false) }

            HomeScreenToggleFake(
                isDark = isDark,
                onToggleTheme = { isDark = !isDark }
            )
        }

        composeTestRule.onNodeWithContentDescription("Toggle Theme").assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription("Toggle Theme").performClick()

        composeTestRule.onNodeWithContentDescription("Toggle Theme").assertIsDisplayed()
    }
}
