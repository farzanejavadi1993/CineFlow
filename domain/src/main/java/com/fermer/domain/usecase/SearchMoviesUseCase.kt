package com.fermer.domain.usecase

import com.fermer.domain.model.Movie
import com.fermer.domain.repository.TrendingRepository


class SearchMoviesUseCase(
    private val repository: TrendingRepository
) {
    suspend operator fun invoke(query: String, page : Int): Result<List<Movie>> {
        return try {
            val movies = repository.searchMovies(query,page)
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
