package com.fermer.movie.presentation.state


import app.cash.turbine.test
import com.fermer.domain.model.Movie
import com.fermer.domain.usecase.AddToWatchlistUseCase
import com.fermer.domain.usecase.GetMovieUseCase
import com.fermer.domain.usecase.IsInWatchlistUseCase
import com.fermer.domain.usecase.RemoveFromWatchlistUseCase
import com.fermer.movie.presentation.MovieDetailViewModel
import com.fermer.movie.state.MovieDetailState
import com.fermer.testutils.MainDispatcherRule
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieDetailViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    private lateinit var addToWatchlist: AddToWatchlistUseCase
    private lateinit var removeFromWatchlist: RemoveFromWatchlistUseCase
    private lateinit var isInWatchlist: IsInWatchlistUseCase
    private lateinit var getMovieUseCase: GetMovieUseCase
    private lateinit var viewModel: MovieDetailViewModel

    private val testMovie = Movie(
        1,
        "Interstellar",
        "2014-11-07",
        "/poster.jpg",
        8.5,
        ""
    )


    @Before
    fun setUp() {
        addToWatchlist = mockk(relaxed = true)
        removeFromWatchlist = mockk(relaxed = true)
        isInWatchlist = mockk()
        getMovieUseCase = mockk()

        viewModel = MovieDetailViewModel(
            addToWatchlist,
            removeFromWatchlist,
            isInWatchlist,
            getMovieUseCase
        )
    }

    @Test
    fun `checkWatchlist sets isSaved to true`() = runTest {
        coEvery { isInWatchlist(1) } returns true

        viewModel.checkWatchlist(1)

        viewModel.state.test {
            assertEquals(MovieDetailState(movie = Movie(0), isSaved = true),
                awaitItem())
            cancel()

        }
    }

    @Test
    fun `toggleWatchlist adds movie when not saved`() = runTest {
        coEvery { isInWatchlist(any()) } returns false
        viewModel.checkWatchlist(1)

        viewModel.toggleWatchlist(testMovie)

        viewModel.state.test {
            awaitItem() // for checkWatchlist
            assertEquals(
                MovieDetailState(movie = Movie(0), isSaved = true),
                awaitItem()
            ) // after add
            cancel()
        }

        coVerify { addToWatchlist(testMovie) }
        coVerify(exactly = 0) { removeFromWatchlist(any()) }
    }

    @Test
    fun `toggleWatchlist removes movie when already saved`() = runTest {
        coEvery { isInWatchlist(any()) } returns true
        viewModel.checkWatchlist(1)
        viewModel.toggleWatchlist(testMovie)

        viewModel.state.test {
            awaitItem() // for checkWatchlist
            assertEquals(
                MovieDetailState(movie = Movie(0), isSaved = false),
                awaitItem()
            )// after remove
            cancel()
        }

        coVerify { removeFromWatchlist(testMovie) }
        coVerify(exactly = 0) { addToWatchlist(any()) }
    }
}
