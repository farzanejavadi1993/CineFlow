package com.fermer.dataa.repository

import com.fermer.dataa.local.dao.MovieDao
import com.fermer.dataa.mapper.toEntity
import com.fermer.dataa.mapper.toMovie
import com.fermer.domain.model.Movie
import com.fermer.domain.repository.WatchlistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

class WatchlistRepositoryImpl @Inject constructor(
    private val dao: MovieDao
) : WatchlistRepository {

    override fun getWatchlist(): Flow<List<Movie>> {
        return dao.getAllMovies().map { list ->
            list.map { it.toMovie() }
        }
    }

    override suspend fun addToWatchlist(movie: Movie) {
        dao.insertMovie(movie.toEntity())
    }

    override suspend fun removeFromWatchlist(movie: Movie) {
        dao.deleteMovie(movie.toEntity())
    }

    override suspend fun isInWatchlist(id: Int): Boolean {
        return dao.isInWatchlist(id)
    }
}
