package com.fermer.trending.presentation.trendinglist


import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.fermer.model.Movie
import com.fermer.trending.presentation.state.TrendingState
import org.junit.Rule
import org.junit.Test

class TrendingScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Composable
    @Test
    fun Shows_loading_indicator_when_loading() {
        composeTestRule.setContent {
            TrendingScreenFake(state = TrendingState(isLoading = true))
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }

    @Test
    fun shows_empty_message_when_no_movies() {
        composeTestRule.setContent {
            TrendingScreenFake(state = TrendingState(movies = emptyList(), isLoading = false))
        }

        composeTestRule.onNodeWithText("فیلمی برای نمایش وجود ندارد.").assertIsDisplayed()
    }

    @Test
    fun shows_movies_when_available() {
        val movies = listOf(
            Movie(1, "فیلم ترند ۱", "/poster1.jpg", "overview", 7.1, "2024-01-01"),
            Movie(2, "فیلم ترند ۲", "/poster2.jpg", "overview", 6.5, "2024-02-02")
        )

        composeTestRule.setContent {
            TrendingScreenFake(state = TrendingState(movies = movies, isLoading = false))
        }

        composeTestRule.onNodeWithText("فیلم ترند ۱").assertIsDisplayed()
        composeTestRule.onNodeWithText("فیلم ترند ۲").assertIsDisplayed()
    }
}