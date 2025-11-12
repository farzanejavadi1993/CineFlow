package com.fermer.dataa.repository

import com.fermer.dataa.mapper.toMovie
import com.fermer.dataa.remote.api.MovieApiService
import com.fermer.domain.model.Movie
import com.fermer.domain.repository.TrendingRepository
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val api: MovieApiService
) : TrendingRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return api.getPopularMovies().results.map { it.toMovie() }
    }

    override suspend fun getMovie(movieId: Int): Movie {
        return api.getMovie(movieId).toMovie()
    }
}
