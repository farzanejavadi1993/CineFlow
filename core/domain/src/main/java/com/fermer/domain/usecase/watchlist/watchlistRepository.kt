package com.fermer.domain.usecase.watchlist

import com.fermer.model.Movie
import kotlinx.coroutines.flow.Flow

interface WatchlistRepository {

    fun getWatchlist(): Flow<List<Movie>>

    suspend fun addToWatchlist(movie: Movie)

    suspend fun removeFromWatchlist(movie: Movie)

    suspend fun isInWatchlist(id: Int): Boolean

}