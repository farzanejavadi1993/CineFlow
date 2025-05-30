package com.fermer.watchlist.data

import com.fermer.database.MovieDao
import com.fermer.domain.usecase.watchlist.WatchlistRepository
import com.fermer.model.Movie
import com.fermer.watchlist.mapper.toEntity
import com.fermer.watchlist.mapper.toMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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
