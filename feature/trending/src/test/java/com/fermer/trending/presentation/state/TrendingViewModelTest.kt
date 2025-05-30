package com.fermer.trending.presentation.state


import app.cash.turbine.test
import com.fermer.domain.usecase.trending.GetTrendingMoviesUseCase
import com.fermer.model.Movie
import com.fermer.testutils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TrendingViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var getTrendingMoviesUseCase: GetTrendingMoviesUseCase
    private lateinit var viewModel: TrendingViewModel

    private val fakeMovies = listOf(
        Movie(1, "Interstellar", "2014-11-07", "/poster1.jpg", 8.6, ""),
        Movie(2, "Oppenheimer", "2023-07-21", "/poster2.jpg", 9.0, "")
    )

    @Before
    fun setup() {
        getTrendingMoviesUseCase = mockk()
        viewModel = TrendingViewModel(getTrendingMoviesUseCase)
    }

    @Test
    fun `load trending movies successfully updates state`() = runTest {
        coEvery { getTrendingMoviesUseCase() } returns Result.success(fakeMovies)

        viewModel.onIntent(TrendingIntent.LoadTrendingMovies)

        viewModel.state.test {
            assertTrue(awaitItem().isLoading)
            val finalState = awaitItem()
            assertFalse(finalState.isLoading)
            assertEquals(fakeMovies, finalState.movies)
            assertNull(finalState.error)
            cancel()
        }
    }

    @Test
    fun `load trending movies with error updates error state`() = runTest {
        val errorMessage = "Network error"
        coEvery { getTrendingMoviesUseCase() } returns Result.failure(Exception(errorMessage))

        viewModel.onIntent(TrendingIntent.LoadTrendingMovies)

        viewModel.state.test {
            assertTrue(awaitItem().isLoading)
            val finalState = awaitItem()
            assertFalse(finalState.isLoading)
            assertEquals(errorMessage, finalState.error)
            assertTrue(finalState.movies.isEmpty())
            cancel()
        }
    }
}

