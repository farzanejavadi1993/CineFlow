package com.fermer.movie.presentation.detail

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.fermer.domain.model.Movie
import com.fermer.movie.component.MovieDetailContent
import org.junit.Rule
import org.junit.Test


class MovieDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeMovie = Movie(
        id = 1,
        title = "فیلم تستی",
        posterUrl = "/poster.jpg",
        overview = "این یک توضیح تستی برای فیلم است.",
        rating = 8.0,
        releaseDate = "2024-01-01"
    )

    @Test
    fun displays_movie_details_correctly() {
        composeTestRule.setContent {
            MovieDetailContent(
                movie = fakeMovie,
                isInWatchlist = false,
                onToggleWatchlist = {}
            )
        }

        composeTestRule.onNodeWithText("فیلم تستی").assertIsDisplayed()
        composeTestRule.onNodeWithText("تاریخ انتشار: 2024-01-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("امتیاز: 8.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("این یک توضیح تستی برای فیلم است.").assertIsDisplayed()
    }

    @Test
    fun shows_add_button_when_not_in_watchlist() {
        composeTestRule.setContent {
            MovieDetailContent(
                movie = fakeMovie,
                isInWatchlist = false,
                onToggleWatchlist = {}
            )
        }

        composeTestRule.onNodeWithText("افزودن به علاقه‌مندی‌ها 🤍").assertIsDisplayed()
    }

    @Test
    fun shows_remove_button_when_in_watchlist() {
        composeTestRule.setContent {
            MovieDetailContent(
                movie = fakeMovie,
                isInWatchlist = true,
                onToggleWatchlist = {}
            )
        }

        composeTestRule.onNodeWithText("حذف از علاقه‌مندی‌ها ❤️").assertIsDisplayed()
    }

    @Test
    fun clicking_button_triggers_onToggleWatchlist() {
        var toggled = false

        composeTestRule.setContent {
            MovieDetailContent(
                movie = fakeMovie,
                isInWatchlist = false,
                onToggleWatchlist = { toggled = true }
            )
        }

        composeTestRule.onNodeWithText("افزودن به علاقه‌مندی‌ها 🤍").performClick()
        assert(toggled)
    }
}
