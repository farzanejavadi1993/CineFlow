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
        title = "movie",
        posterUrl = "/poster.jpg",
        overview = "description",
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

        composeTestRule.onNodeWithText("movie").assertIsDisplayed()
        composeTestRule.onNodeWithText("Release Date: 2024-01-01").assertIsDisplayed()
        composeTestRule.onNodeWithText("8.0").assertIsDisplayed()
        composeTestRule.onNodeWithText("description").assertIsDisplayed()
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

        composeTestRule.onNodeWithText("Add to watchlist 🤍").assertIsDisplayed()
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

        composeTestRule.onNodeWithText("Remove from watchlist ❤️").assertIsDisplayed()
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

        composeTestRule.onNodeWithText("Add to watchlist 🤍").performClick()
        assert(toggled)
    }
}
