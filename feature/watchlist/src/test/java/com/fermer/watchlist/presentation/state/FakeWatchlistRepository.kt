package com.fermer.watchlist.presentation.state

import com.fermer.domain.usecase.watchlist.WatchlistRepository
import com.fermer.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeWatchlistRepository : WatchlistRepository {

    private val watchlist = mutableListOf<Movie>()

    override fun getWatchlist(): Flow<List<Movie>> {
        return flowOf(watchlist)
    }

    override suspend fun addToWatchlist(movie: Movie) {
        if (!watchlist.contains(movie)) {
            watchlist.add(movie)
        }
    }

    override suspend fun removeFromWatchlist(movie: Movie) {
        watchlist.remove(movie)
    }

    override suspend fun isInWatchlist(movieId: Int): Boolean {
        return watchlist.any { it.id == movieId }
    }
}
