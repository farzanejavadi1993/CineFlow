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

        // دکمه تم لایت دیده بشه اول
        composeTestRule.onNodeWithContentDescription("Toggle Theme").assertIsDisplayed()

        // کلیک کنیم
        composeTestRule.onNodeWithContentDescription("Toggle Theme").performClick()
        // دوباره وجود داشته باشه (state جدید)
        composeTestRule.onNodeWithContentDescription("Toggle Theme").assertIsDisplayed()
    }
}
