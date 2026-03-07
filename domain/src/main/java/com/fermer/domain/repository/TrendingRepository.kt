package com.fermer.domain.repository

import com.fermer.domain.model.Movie


interface TrendingRepository {
    suspend fun getTrendingMovies(page: Int): List<Movie>
    suspend fun getMovie(movieId: Int): Movie

    suspend fun searchMovies(query: String, page : Int): List<Movie>

}