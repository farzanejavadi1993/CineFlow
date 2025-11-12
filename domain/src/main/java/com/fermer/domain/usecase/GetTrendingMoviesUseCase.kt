package com.fermer.domain.usecase

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.TrendingRepository

class GetTrendingMoviesUseCase(
    private val repository: TrendingRepository
) {
    suspend operator fun invoke(): Result<List<Movie>> {
        return try {
            val movies = repository.getTrendingMovies()
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}