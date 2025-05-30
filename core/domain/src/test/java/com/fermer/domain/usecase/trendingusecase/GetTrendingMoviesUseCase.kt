package com.fermer.domain.usecase.trendingusecase


import com.fermer.domain.usecase.trending.GetTrendingMoviesUseCase
import com.fermer.domain.usecase.trending.TrendingRepository
import com.fermer.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetTrendingMoviesUseCaseTest {

    private lateinit var repository: TrendingRepository
    private lateinit var useCase: GetTrendingMoviesUseCase

    private val fakeMovies = listOf(
        Movie(1, "Inception", "2010-07-16", "/poster.jpg", 8.8, "")
    )

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetTrendingMoviesUseCase(repository)
    }

    @Test
    fun `invoke returns success with movie list`() = runTest {
        coEvery { repository.getTrendingMovies() } returns fakeMovies

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(fakeMovies, result.getOrNull())
    }

    @Test
    fun `invoke returns failure on exception`() = runTest {
        val errorMsg = "Server error"
        coEvery { repository.getTrendingMovies() } throws RuntimeException(errorMsg)

        val result = useCase()

        assertTrue(result.isFailure)
        assertEquals(errorMsg, result.exceptionOrNull()?.message)
    }
}
