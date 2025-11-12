package com.fermer.domain.usecase.watchlist

import com.fermer.domain.repository.WatchlistRepository
import com.fermer.domain.usecase.IsInWatchlistUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class IsInWatchlistUseCaseTest {

    private lateinit var repository: WatchlistRepository
    private lateinit var useCase: IsInWatchlistUseCase

    @Before
    fun setUp() {
        repository = mockk()
        useCase = IsInWatchlistUseCase(repository)
    }

    @Test
    fun `invoke returns true when movie is in watchlist`() = runTest {
        val movieId = 10
        coEvery { repository.isInWatchlist(movieId) } returns true

        val result = useCase(movieId)

        assert(result)
    }
}
