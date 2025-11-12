package com.fermer.domain.usecase

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.TrendingRepository

class GetMovieUseCase(
private val repository: TrendingRepository
) {
    suspend operator fun invoke(movieId: Int): Result<Movie>{
        return try {
            val movies = repository.getMovie(movieId = movieId)
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}