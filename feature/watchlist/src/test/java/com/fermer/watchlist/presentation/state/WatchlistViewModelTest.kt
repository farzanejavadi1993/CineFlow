package com.fermer.watchlist.presentation.state

import app.cash.turbine.test
import com.fermer.domain.model.Movie
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule


import com.fermer.domain.usecase.GetWatchlistUseCase
import com.fermer.domain.usecase.RemoveFromWatchlistUseCase
import com.fermer.testutils.MainDispatcherRule
import com.fermer.watchlist.WatchlistIntent
import com.fermer.watchlist.presentation.WatchlistViewModel
import io.mockk.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Assert.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WatchlistViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var getWatchlistUseCase: GetWatchlistUseCase
    private lateinit var removeFromWatchlistUseCase: RemoveFromWatchlistUseCase
    private lateinit var viewModel: WatchlistViewModel

    private val fakeMovies = listOf(
        Movie(
            1,
            "Fight Club",
            "1999-10-15",
            "/poster1.jpg",
            6.5,
            ""
        ),
        Movie(
            2,
            "Inception",
            "2010-07-16",
            "/poster2.jpg",
            7.2,
            ""
        )
    )

    @Before
    fun setUp() {
        getWatchlistUseCase = mockk()
        removeFromWatchlistUseCase = mockk(relaxed = true)

        viewModel = WatchlistViewModel(
            getWatchlistUseCase = getWatchlistUseCase,
            removeFromWatchlistUseCase = removeFromWatchlistUseCase
        )
    }

    @Test
    fun `loadWatchlist emits movies list`() = runTest {
        coEvery { getWatchlistUseCase() } returns flowOf(fakeMovies)

        viewModel.onIntent(WatchlistIntent.LoadWatchlist)

        viewModel.state.test {
            val initial = awaitItem()
            assertTrue(initial.isLoading)

            val final = awaitItem()
            assertFalse(final.isLoading)
            assertEquals(2, final.movies.size)
            assertEquals("Fight Club", final.movies[0].title)

            cancel()
        }
    }

    @Test
    fun `removeMovieById calls removeFromWatchlistUseCase`() = runTest {
        coEvery { getWatchlistUseCase() } returns flowOf(fakeMovies)

        viewModel.onIntent(WatchlistIntent.LoadWatchlist)
        advanceUntilIdle()

        viewModel.onIntent(WatchlistIntent.RemoveMovie(2))

        coVerify { removeFromWatchlistUseCase(fakeMovies[1]) }
    }

    @Test
    fun `loadWatchlist handles error correctly`() = runTest {
        val errorMsg = "Network error"
        coEvery { getWatchlistUseCase() } returns flow {
            throw RuntimeException(errorMsg)
        }

        viewModel.onIntent(WatchlistIntent.LoadWatchlist)

        viewModel.state.test {
            val initial = awaitItem()
            assertTrue(initial.isLoading)

            val final = awaitItem()
            assertFalse(final.isLoading)
            assertEquals(errorMsg, final.error)

            cancel()
        }
    }

}
