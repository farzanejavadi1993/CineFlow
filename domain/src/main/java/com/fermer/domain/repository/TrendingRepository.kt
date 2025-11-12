package com.fermer.domain.repository

import com.fermer.domain.model.Movie


interface TrendingRepository {
    suspend fun getTrendingMovies(): List<Movie>
    suspend fun getMovie(movieId: Int): Movie
}