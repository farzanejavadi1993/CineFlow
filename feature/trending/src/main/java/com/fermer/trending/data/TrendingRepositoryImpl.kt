package com.fermer.trending.presentation.data

import com.fermer.domain.usecase.trending.TrendingRepository
import com.fermer.model.Movie
import com.fermer.network.api.TmdbApiService
import com.fermer.network.mapper.toMovie
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val api: TmdbApiService
) : TrendingRepository {

    override suspend fun getTrendingMovies(): List<Movie> {
        return api.getPopularMovies().results.map { it.toMovie() }
    }
}
