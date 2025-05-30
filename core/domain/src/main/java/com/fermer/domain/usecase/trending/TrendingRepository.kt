package com.fermer.domain.usecase.trending

import com.fermer.model.Movie

interface TrendingRepository {
    suspend fun getTrendingMovies(): List<Movie>
}