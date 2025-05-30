package com.fermer.watchlist.presentaion.favorites


import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.fermer.model.Movie
import com.fermer.watchlist.presentation.state.WatchlistState
import com.fermer.watchlist.presentation.favorites.WatchlistItem
import org.junit.Rule
import org.junit.Test

class WatchlistItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun item_shows_movie_title_and_date() {
        val movie = Movie(1, "فیلم تستی", "/poster.jpg", "توضیح تست",
            7.0, "2023-12-31")

        composeTestRule.setContent {
            WatchlistItem(
                movie = movie,
                onClick = {},
                onRemove = {}
            )
        }

        composeTestRule.onNodeWithText("فیلم تستی").assertIsDisplayed()
        composeTestRule.onNodeWithText("2023-12-31").assertIsDisplayed()
    }

    @Test
    fun clicking_item_triggers_onClick() {
        var clicked = false
        val movie = Movie(1, "فیلم کلیکی", "/poster.jpg", "...",
            6.9, "2023-11-11")

        composeTestRule.setContent {
            WatchlistItem(
                movie = movie,
                onClick = { clicked = true },
                onRemove = {}
            )
        }

        composeTestRule.onNodeWithText("فیلم کلیکی").performClick()
        assert(clicked)
    }

    @Test
    fun clicking_heart_icon_triggers_onRemove() {
        var removed = false
        val movie = Movie(1, "فیلم حذف‌شونده", "/poster.jpg", "desc"
            , 6.5, "2023-10-10")

        composeTestRule.setContent {
            WatchlistItem(
                movie = movie,
                onClick = {},
                onRemove = { removed = true }
            )
        }

        composeTestRule.onAllNodesWithContentDescription("Remove from watchlist")
            .onFirst()
            .performClick()

        assert(removed)
    }


    @Test
    fun show_empty_message_when_no_movies() {
        val emptyState = WatchlistState(movies = emptyList(), isLoading = false)

        composeTestRule.setContent {
            WatchlistScreenFake(state = emptyState)
        }

        composeTestRule.onNodeWithText("هیچ فیلمی در علاقه‌مندی‌ها نیست.").assertIsDisplayed()
    }

    @Test
    fun display_movies_when_state_has_movies() {
        val movies = listOf(
            Movie(1, "فیلم تستی ۱", "/poster1.jpg", "توضیح", 7.1, "2023-01-01"),
            Movie(2, "فیلم تستی ۲", "/poster2.jpg", "توضیح", 6.8, "2023-02-02")
        )
        val state = WatchlistState(movies = movies, isLoading = false)

        composeTestRule.setContent {
            WatchlistScreenFake(state = state)
        }

        composeTestRule.onNodeWithText("فیلم تستی ۱").assertIsDisplayed()
        composeTestRule.onNodeWithText("فیلم تستی ۲").assertIsDisplayed()
    }
}
