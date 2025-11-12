package com.fermer.domain.usecase.watchlist


import com.fermer.domain.model.Movie
import com.fermer.domain.repository.WatchlistRepository
import com.fermer.domain.usecase.AddToWatchlistUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddToWatchlistUseCaseTest {

    private lateinit var repository: WatchlistRepository
    private lateinit var useCase: AddToWatchlistUseCase

    private val testMovie = Movie(
        1, "Batman",
        "2005-06-15", "/poster.jpg", 8.0, ""
    )

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = AddToWatchlistUseCase(repository)
    }

    @Test
    fun `invoke calls repository with correct movie`() = runTest {
        useCase(testMovie)

        coVerify { repository.addToWatchlist(testMovie) }
    }
}
