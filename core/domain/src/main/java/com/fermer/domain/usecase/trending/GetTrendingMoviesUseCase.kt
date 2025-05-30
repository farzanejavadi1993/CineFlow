package com.fermer.domain.usecase.trending

import com.fermer.model.Movie

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

