package com.fermer.domain.usecase.watchlist

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.WatchlistRepository
import com.fermer.domain.usecase.RemoveFromWatchlistUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RemoveFromWatchlistUseCaseTest {

    private lateinit var repository: WatchlistRepository
    private lateinit var useCase: RemoveFromWatchlistUseCase

    private val testMovie = Movie(2, "Joker", "2019-10-04", "/poster2.jpg", 7.9, "")

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = RemoveFromWatchlistUseCase(repository)
    }

    @Test
    fun `invoke calls repository to remove movie`() = runTest {
        useCase(testMovie)

        coVerify { repository.removeFromWatchlist(testMovie) }
    }
}
